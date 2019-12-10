package com.shafi.springmanagement.helper.response;

import com.shafi.springmanagement.helper.error.BaseException;

public class ErrorResponse extends BasedResponse {
    public String errorCode;
    public String errorMessage;
    public ErrorResponse(String message) {
        this(new BaseException(message));
    }

    public ErrorResponse(BaseException exception) {
        this.isSuccess = false;
        this.errorCode = exception.code;
        this.errorMessage = exception.getMessage();
    }

}
