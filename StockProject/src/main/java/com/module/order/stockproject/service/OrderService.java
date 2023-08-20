package com.module.order.stockproject.service;

import com.module.order.stockproject.api.StockClient;
import com.module.order.stockproject.dto.BuyRequest;
import com.module.order.stockproject.dto.OrderResponse;
import com.module.order.stockproject.entity.User;
import com.module.order.stockproject.exception.ErrorCode;
import com.module.order.stockproject.exception.UserNotFoundException;
import com.module.order.stockproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final UserRepository userRepository;
    private final StockClient stockClient;
    private final OrderRepoService orderRepoService;



    public OrderResponse buyProduct(BuyRequest buyRequest, LocalDateTime now){
        User user = userRepository.findById(buyRequest.getUserId())
                .orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND));

        validateStockQuantity(buyRequest.getStockId(), buyRequest.getQuantity());
        validateUserHasEnoughMoney(user, buyRequest.getStockId(), buyRequest.getQuantity());
        return orderRepoService.makeOrder(user, buyRequest.getStockId(), now);
    }


    private void validateStockQuantity(long stockId, long quantity){
        stockClient.validateStockQuantity(stockId, quantity);
    }

    private void validateUserHasEnoughMoney(User user, long stockId, long quantity){
        long stockPrice = stockClient.getStockPrice(stockId);
        user.canBuy(stockPrice * quantity);
    }

}
