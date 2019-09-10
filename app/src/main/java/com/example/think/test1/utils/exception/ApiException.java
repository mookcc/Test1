package com.example.think.test1.utils.exception;

public class ApiException extends Exception {
    private int code;
    private String displayMessage;

    public ApiException(int code) {
        this.code = code;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public int getCode() {
        return code;
    }

}
