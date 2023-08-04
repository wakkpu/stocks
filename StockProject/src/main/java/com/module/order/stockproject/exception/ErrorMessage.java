package com.module.order.stockproject.exception;

public enum ErrorMessage {
    USER_NOT_FOUND(404,"해당 유저를 찾을 수 없습니다.");

    private final int errorCode;
    private final String errorMessage;

    ErrorMessage(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
