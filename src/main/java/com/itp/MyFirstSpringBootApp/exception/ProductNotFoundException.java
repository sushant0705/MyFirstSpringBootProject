package com.itp.MyFirstSpringBootApp.exception;
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }
}

