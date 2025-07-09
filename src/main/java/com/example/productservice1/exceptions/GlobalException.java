package com.example.productservice1.exceptions;

import com.example.productservice1.dtos.ExceptionDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(NullPointerException.class)
    public ExceptionDto handleNullPointerEception(){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setStatus("failure");
        exceptionDto.setMessage("Nullpointer exception occured");

        return exceptionDto;
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ExceptionDto handleProductNotFoundEception(ProductNotFoundException productNotFoundException){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setStatus("failure");
        exceptionDto.setMessage(productNotFoundException.getMessage());

        return exceptionDto;
    }
}
