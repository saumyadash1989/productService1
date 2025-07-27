package com.example.productservice1.calculator;

import org.springframework.stereotype.Controller;

@Controller
public class CalculatorController {
      private CalculatorService calculatorService;
      public CalculatorController(CalculatorService calculatorService) {
          this.calculatorService = calculatorService;
      }
      public int add(int a , int b){
          System.out.println("In Controller");
          System.out.println("Some login before add");
          int result = calculatorService.addInService(a,b);
          System.out.println("Some login after add");
          return result;
      }
}
