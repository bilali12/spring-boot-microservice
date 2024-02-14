package com.allesCoders.orderservice.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return errors;
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler({SomeProductsNotInStockException.class})
    public Map<String, String> someNotInStock(SomeProductsNotInStockException e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", e.getMessage());

        return error;
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({ProductNotExistException.class})
    public Map<String, String> productNotExist(ProductNotExistException e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", e.getMessage());

        return error;
    }
}
