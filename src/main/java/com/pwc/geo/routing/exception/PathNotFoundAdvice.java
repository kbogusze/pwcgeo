package com.pwc.geo.routing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PathNotFoundAdvice {

    @ExceptionHandler(PathNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String employeeNotFoundHandler(PathNotFoundException ex) {
        return ex.getMessage();
    }
}
