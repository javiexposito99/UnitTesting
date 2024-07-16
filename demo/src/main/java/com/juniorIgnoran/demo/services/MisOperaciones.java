package com.juniorIgnoran.demo.services;


import org.springframework.stereotype.Service;

@Service
public class MisOperaciones implements Operaciones {
    @Override
    public int factorial(int num) {
        // n -> n * (n-1) * (n*2) .... * 1
        //n >= 0
        //factorial(4)
        if(num < 0) {
            throw new ArithmeticException(); //Una excepción de un concepto matematico.
        }

        if(num > 23) {
            throw new IllegalArgumentException("Overflow from integer");
        }

        if (num == 1 || num == 0) return 1;
        return num * factorial(num-1);
    }

}
