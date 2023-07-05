package com.example.springboot.exceptions;

public class CustomErrorResponse {

    private String message;

    public CustomErrorResponse() {
        this.message = "";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
