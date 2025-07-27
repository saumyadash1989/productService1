package com.example.productservice1.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
@SpringBootTest
public class CalculatorControlerTest {

//    CalculatorService calculatorService
//            = Mockito.mock(CalculatorService.class);
//    CalculatorController calculatorController
//            = new CalculatorController(calculatorService);
    @MockitoBean
    CalculatorService calculatorService;
    @Autowired
    CalculatorController  calculatorController;
    @Test
  public void testAddWhenTwoIntersArePassedAndReturnTwoIntegers(){
        when(calculatorService.addInService(5,6)).
                thenReturn(11);
        when(calculatorService.addInService(6,10)).
                thenReturn(16);
      //Arrange
      int a =5;
      int b =6;
      int c=6;
      int d=10;
      int expectedResult = 11;
      int anotherExpectedValue=16;

      //Act
      int actualResult =calculatorController.add(a,b);
        int anotherResult =calculatorController.add(c,d);
      //Assert
      Assertions.assertEquals(expectedResult,actualResult);
      Assertions.assertEquals(anotherExpectedValue,anotherResult);
  }
    @Test
    public void testAddWhenAnyIntersArePassedAndReturnTwoIntegers(){
        when(calculatorService.addInService(anyInt(),anyInt())).
                thenReturn(11);

        //Arrange

        int c=600;
        int d=1000;
        int expectedResult = 11;
        int anotherExpectedValue=11;

        //Act

        int anotherResult =calculatorController.add(c,d);
        //Assert

        Assertions.assertEquals(anotherExpectedValue,anotherResult);
    }
}
