package com.module.order.stockproject.exception;

public class ApiConnectionFailException extends RuntimeException{
    ErrorMessage errorMessage;

    public ApiConnectionFailException(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }
}
