package com.module.order.stockproject.dto;

import com.module.order.stockproject.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class OrderResponse {
    private Long id;
    private Long stockId;
    private UserResponse userResponse;
    private LocalDateTime orderTime;


    @Builder
    private OrderResponse(Long id, Long stockId, UserResponse userResponse, LocalDateTime orderTime) {
        this.id = id;
        this.stockId = stockId;
        this.userResponse = userResponse;
        this.orderTime = orderTime;
    }

    public static OrderResponse fromEntity(Order order){
        return OrderResponse.builder()
                .id(order.getId())
                .stockId(order.getStockId())
                .userResponse(UserResponse.fromEntity(order.getUser()))
                .orderTime(order.getOrderTime())
                .build();



    }
}
