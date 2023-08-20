package com.module.order.stockproject.exception;

public class ApiConnectionFailException extends RuntimeException{
    ErrorCode errorCode;

    public ApiConnectionFailException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }
}
