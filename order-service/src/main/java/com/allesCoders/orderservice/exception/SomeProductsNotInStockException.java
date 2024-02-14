package com.allesCoders.orderservice.exception;

public class SomeProductsNotInStockException extends RuntimeException{
    public SomeProductsNotInStockException(String msg) {
        super(msg);
    }
}
