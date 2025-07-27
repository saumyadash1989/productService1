package com.example.productservice1.calculator;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public int addInService(int a ,int b)
    {
        System.out.println("addInService");
        System.out.println("some logic before Add");
        int result = a+b;
        return result;
    }
}
