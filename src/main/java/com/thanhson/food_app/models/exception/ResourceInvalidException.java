package com.thanhson.food_app.models.exception;

public class ResourceInvalidException extends RuntimeException{
    public ResourceInvalidException(String message) {
        super(message);
    }
}
