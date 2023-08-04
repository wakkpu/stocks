package com.module.order.stockproject.exception;

public class UserNotFoundException extends RuntimeException {
    ErrorMessage errorMessage;

    public UserNotFoundException(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }
}
