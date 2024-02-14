package com.allesCoders.orderservice.exception;

public class ProductNotExistException extends RuntimeException{
    public ProductNotExistException(String msg) {
        super(msg);
    }
}
