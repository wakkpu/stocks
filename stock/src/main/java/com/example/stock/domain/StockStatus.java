package com.example.stock.domain;

public enum StockStatus {
    SELLING("판매중"),
    READY("준비중"),
    NON_SELLING("판매중단");

    private String name;

    StockStatus(String name) {
        this.name = name;
    }
}
