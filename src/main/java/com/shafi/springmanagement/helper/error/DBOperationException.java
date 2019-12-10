package com.shafi.springmanagement.helper.error;

public class DBOperationException extends BaseException {

    public DBOperationException() {
        this("Database Operation Failed!!");
    }

    public DBOperationException(String message) {
        super(message);
        this.message = message;
        this.code = "USER5001";
    }

}
