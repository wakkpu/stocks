package com.module.order.stockproject.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND(404,"해당 유저를 찾을 수 없습니다."),
    INSUFFICIENT_BALANCE(400,"잔액이 부족합니다."),
    API_CONNECTION_FAILED(500,"API 통신에 실패했습니다.");

    private final int errorCode;
    private final String errorMessage;

    ErrorCode(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
