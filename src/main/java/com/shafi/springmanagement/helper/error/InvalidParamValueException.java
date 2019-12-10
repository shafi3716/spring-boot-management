package com.shafi.springmanagement.helper.error;

public class InvalidParamValueException extends BaseException{

    public InvalidParamValueException() {
        this("Invalid parameter value!!!");
    }

    public InvalidParamValueException(String message) {
        super(message);
        this.message = message;
        this.code = "PLY5000";
    }

}
