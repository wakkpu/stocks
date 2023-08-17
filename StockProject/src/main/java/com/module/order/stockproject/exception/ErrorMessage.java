package com.module.order.stockproject.exception;

public enum ErrorMessage {
    USER_NOT_FOUND(404,"해당 유저를 찾을 수 없습니다."),
    API_CONNECTION_FAILED(500,"API 통신에 실패했습니다.");

    private final int errorCode;
    private final String errorMessage;

    ErrorMessage(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
