package com.juniorIgnoran.demo.controllers;

import com.juniorIgnoran.demo.services.Operaciones;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
        })
public class OperacionesController {

    @MockBean
    private Operaciones operacionesMock;

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    public void setup(){
        Mockito.when(operacionesMock.factorial(ArgumentMatchers.anyInt())).thenReturn(0);
    }

    @Test
    public void factorialControllerTest(){
        webTestClient.get()
                .uri("/factorial?numero=10")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.message")
                .isEqualTo("resultado: 0");
    }

    @Test
    public void factorialControllerFailTest(){

        Mockito
                .when(
                        operacionesMock
                                .factorial(ArgumentMatchers.anyInt()))
                .thenThrow(ArithmeticException.class);
        webTestClient.get()
                .uri("/factorial?numero=-1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.message")
                .isEqualTo("Operacion invalida");

    }

}
