package com.module.order.stockproject.service;

import com.module.order.stockproject.entity.Order;
import com.module.order.stockproject.entity.User;
import com.module.order.stockproject.exception.ErrorMessage;
import com.module.order.stockproject.exception.UserNotFoundException;
import com.module.order.stockproject.repository.OrderRepository;
import com.module.order.stockproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Transactional
    public void buy(long userId, long stockId) {
        /*
        checkStock(stockId)
        사용자로부터 입력받은 stockId를 넘겨, checkStock(stockId) 메서드 안에서 재고시스템으로 통신을 보냈다 가정
        재고가 있다는 200 상태코드를 받았다고 가정
         */
//        checkStock();
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));//
        Order order = Order.builder()
                .user(user)
                .stockId(stockId)
                .orderTime(LocalDateTime.now())
                .build();
        orderRepository.save(order);
    }

    public void buy2(long userId, long stockId){
        checkStock();
        buy(userId,stockId);
    }

    public boolean checkStock(){
        return true;
    }

}
