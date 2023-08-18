package com.example.stock.dto;

import com.example.stock.domain.Stock;
import com.example.stock.domain.StockStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class StockDto {
    Long id;
    private String name;
    private int price;
    private int amount;
    StockStatus stockStatus;

    @Builder
    private StockDto(Long id, String name, int price, int amount, StockStatus stockStatus) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.stockStatus = stockStatus;
    }

    public static StockDto entityToDto(Stock stock){
        return StockDto.builder()
                .id(stock.getId())
                .amount(stock.getAmount())
                .price(stock.getPrice())
                .build();
    }
}
