package com.ms1.productservicemicroservices.controllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<String> handleArithmeticException(){
        ResponseEntity<String>response= new ResponseEntity<>(
                "Somthing went wrong coming from ControllerAdvice",
                HttpStatus.NOT_FOUND
        );
        return response;
    }
}