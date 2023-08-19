package com.module.order.stockproject.service;

import com.module.order.stockproject.api.StockClient;
import com.module.order.stockproject.dto.BuyRequest;
import com.module.order.stockproject.dto.OrderResponse;
import com.module.order.stockproject.entity.Order;
import com.module.order.stockproject.entity.User;
import com.module.order.stockproject.exception.ErrorMessage;
import com.module.order.stockproject.exception.UserNotFoundException;
import com.module.order.stockproject.repository.OrderRepository;
import com.module.order.stockproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final StockClient stockClient;

    @Transactional
    public OrderResponse buyProduct(BuyRequest buyRequest, LocalDateTime now){
        User user = userRepository.findById(buyRequest.getUserId())
                .orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));

        stockValidation(buyRequest.getStockId(), buyRequest.getQuantity());
//        buyValidation(user, buyRequest.getStockId(), buyRequest.getQuantity());
        return makeOrder(user, buyRequest.getStockId(), now);
    }

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

    private void stockValidation(long stockId, long quantity){
        stockClient.validateStockQuantity(stockId, quantity);

//        // 통신 요청
//        if(통신이 실패하면) throw new 통신실패Exception();
//
//        // 200 400
//        if(재고가 부족하면) throw new 재고부족Exception();
    }

    private void buyValidation(User user, long stockId, long quantity){

        long stockPrice = stockClient.getStockPrice(stockId);
        user.canBuy(stockPrice * quantity);
    }

}
