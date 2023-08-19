package com.module.order.stockproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
public class StockRequest {
    private long stockId;
    private long requireAmount;

    @Builder
    private StockRequest(long stockId, long requireAmount) {
        this.stockId = stockId;
        this.requireAmount = requireAmount;
    }
}
