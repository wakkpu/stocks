package com.example.stock.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String name;
    private int price;
    private int amount;
    @Enumerated(value = EnumType.STRING)
    StockStatus stockStatus;

    public void decreaseAmount(int requestAmount){
        this.amount = this.amount - requestAmount;
    }

}
