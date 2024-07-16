package com.juniorIgnoran.demo.controllers;

import com.juniorIgnoran.demo.services.Operaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Controllers {


    //Inyectando operaciones.
    private Operaciones operaciones;

    @Autowired
    public Controllers(Operaciones operaciones) {
        this.operaciones = operaciones;
    }

    @GetMapping("/")
    public Map<String, String> home() {
        return new HashMap<>(){{put("message", "Hello Unit Tests");}};
    }

    @GetMapping("/factorial")
    public Map<String, String> factorial(
            @RequestParam int numero
    ){
        try {
            return new HashMap<>(){{put("message", "resultado: " +
                    operaciones.factorial(numero)
            );}};
        }catch (ArithmeticException e){
            return new HashMap<>(){{put("message", "operacion invalidad"
            );}};
        }

    }
}
