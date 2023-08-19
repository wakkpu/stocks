package com.module.order.stockproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BuyRequest {
    private long userId;
    private long stockId;
    private long quantity;

    @Builder
    private BuyRequest(long userId, long stockId, long quantity) {
        this.userId = userId;
        this.stockId = stockId;
        this.quantity = quantity;
    }
}
