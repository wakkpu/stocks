package com.module.order.stockproject.service;

import com.module.order.stockproject.dto.OrderResponse;
import com.module.order.stockproject.entity.Order;
import com.module.order.stockproject.entity.User;
import com.module.order.stockproject.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderRepoService {
    private final OrderRepository orderRepository;
    @Transactional
    protected OrderResponse makeOrder(User user, long stockId, LocalDateTime now) {
        Order order = Order.builder()
                .user(user)
                .stockId(stockId)
                .orderTime(now)
                .build();

        Order savedOrder = orderRepository.save(order);
        return OrderResponse.fromEntity(savedOrder);
    }

}
