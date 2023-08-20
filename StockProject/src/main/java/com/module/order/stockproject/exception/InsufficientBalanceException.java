package com.module.order.stockproject.exception;

public class InsufficientBalanceException extends RuntimeException{
    ErrorCode errorCode;


    public InsufficientBalanceException(ErrorCode errorCode){
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }

}
