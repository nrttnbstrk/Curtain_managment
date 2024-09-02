package com.springboot.springbootsecurity.subProduct.exception;

public class ErrorResponse {

    private int status;
    private String message;

    public ErrorResponse(int value, String message) {
        this.status = status;
        this.message = message;
    }

    // Getter ve Setter metodları

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
