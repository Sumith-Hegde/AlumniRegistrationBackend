package com.ESD.AllumniRegistration;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandle extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String handleError(Exception ex) {
        return "ERROR";
    }
}
