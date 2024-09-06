package com.enb.curtainmanagement.subProduct.exception;

public class ErrorResponse {

    private String message;

    public ErrorResponse(int value, String message) {

        this.message = message;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
