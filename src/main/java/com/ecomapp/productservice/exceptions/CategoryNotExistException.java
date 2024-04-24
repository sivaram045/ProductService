package com.ecomapp.productservice.exceptions;

public class CategoryNotExistException extends Exception {
    public CategoryNotExistException(String message) {
        super(message);
    }
}
