package com.module.order.stockproject.exception;

public class UserNotFoundException extends RuntimeException {
    ErrorCode errorCode;

    public UserNotFoundException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }
}
