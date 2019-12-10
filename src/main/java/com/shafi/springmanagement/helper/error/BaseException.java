package com.shafi.springmanagement.helper.error;

public class BaseException extends Exception {
    public String code;
    public String message;

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }
}
