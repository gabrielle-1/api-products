package com.example.springboot.exceptions;

public class NullParameterException extends RuntimeException {
    public NullParameterException(String message) {
        super(message);
    }
}
