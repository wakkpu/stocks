package com.module.order.stockproject.service;

import com.module.order.stockproject.api.StockClient;
import com.module.order.stockproject.dto.BuyRequest;
import com.module.order.stockproject.dto.OrderResponse;
import com.module.order.stockproject.entity.Order;
import com.module.order.stockproject.entity.User;
import com.module.order.stockproject.repository.OrderRepository;
import com.module.order.stockproject.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @MockBean
    private StockClient stockClient;

    private OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        orderService = new OrderService(orderRepository,userRepository,stockClient);
    }

    @DisplayName("")
    @Test
    public void buyProduct(){
        int stockPrice = 100_000;
        BDDMockito.given(stockClient.getStockPrice(anyLong()))
                .willReturn(stockPrice);

        LocalDateTime now = LocalDateTime.now();
        User user = User.builder()
                .userName("test1")
                .build();
        userRepository.save(user);
        BuyRequest buyRequest = BuyRequest.builder()
                .userId(1L)
                .stockId(1L)
                .quantity(1L)
                .build();
        OrderResponse orderResponse = orderService.buyProduct(buyRequest,now);
        assertThat(orderResponse.getId()).isNotNull();
        assertThat(orderResponse).extracting(
            "stockId","orderTime"
        )
        .containsExactlyInAnyOrder(buyRequest.getUserId(),now);
    }

}