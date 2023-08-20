package com.module.order.stockproject.service;

import com.module.order.stockproject.api.StockClient;
import com.module.order.stockproject.dto.BuyRequest;
import com.module.order.stockproject.dto.OrderResponse;
import com.module.order.stockproject.entity.Order;
import com.module.order.stockproject.entity.User;
import com.module.order.stockproject.exception.ApiConnectionFailException;
import com.module.order.stockproject.exception.ErrorCode;
import com.module.order.stockproject.exception.InsufficientBalanceException;
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
    private UserRepository userRepository;
    @Autowired
    private OrderRepoService orderRepoService;

    @MockBean
    private StockClient stockClient;

    private OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        orderService = new OrderService(userRepository,stockClient,orderRepoService);
    }

    @DisplayName("유저 아이디, 제품 번호, 제품 수량을 입력받아 주문을 생성합니다.")
    @Test
    public void buyProduct(){
        // given
        int stockPrice = 100_000;
        int userBalance = 100_000;
        LocalDateTime now = LocalDateTime.now();
        User user = User.builder()
                .userName("test1")
                .balance(userBalance)
                .build();
        User savedUser = userRepository.save(user);
        BuyRequest buyRequest = BuyRequest.builder()
                .userId(savedUser.getId())
                .stockId(1L)
                .quantity(1L)
                .build();

        BDDMockito.given(stockClient.getStockPrice(anyLong()))
                .willReturn(stockPrice);

        BDDMockito.willDoNothing()
                .given(stockClient)
                .validateStockQuantity(anyLong(),anyLong());

        // when
        OrderResponse orderResponse = orderService.buyProduct(buyRequest,now);

        // then
        assertThat(orderResponse.getId()).isNotNull();
        assertThat(orderResponse).extracting(
            "stockId","orderTime"
        )
        .containsExactlyInAnyOrder(buyRequest.getStockId(),now);
    }

    @DisplayName("구매하려는 물건보다 적은 잔액을 가지고 있다면 물건을 구매할 수 없습니다.")
    @Test
    public void buyProduct2(){
        // given
        int stockPrice = 100_000;
        int userBalance = 5_000;
        LocalDateTime now = LocalDateTime.now();
        User user = User.builder()
                .userName("test1")
                .balance(userBalance)
                .build();
        User savedUser = userRepository.save(user);
        BuyRequest buyRequest = BuyRequest.builder()
                .userId(savedUser.getId())
                .stockId(1L)
                .quantity(1L)
                .build();

        BDDMockito.given(stockClient.getStockPrice(anyLong()))
                .willReturn(stockPrice);

        BDDMockito.willDoNothing()
                .given(stockClient)
                .validateStockQuantity(anyLong(),anyLong());

        // when // then
        Assertions.assertThatThrownBy(() -> orderService.buyProduct(buyRequest,now))
                .isInstanceOf(InsufficientBalanceException.class)
                .hasMessage("잔액이 부족합니다.");
    }
    @DisplayName("재고 확인에 문제가 발생했을 때 예외가 발생합니다.")
    @Test
    public void buyProduct3(){
        // given
        int stockPrice = 100_000;
        int userBalance = 100_000;
        LocalDateTime now = LocalDateTime.now();
        User user = User.builder()
                .userName("test1")
                .balance(userBalance)
                .build();
        User savedUser = userRepository.save(user);
        BuyRequest buyRequest = BuyRequest.builder()
                .userId(savedUser.getId())
                .stockId(1L)
                .quantity(1L)
                .build();

        BDDMockito.given(stockClient.getStockPrice(anyLong()))
                .willReturn(stockPrice);

        BDDMockito.willThrow(new ApiConnectionFailException(ErrorCode.API_CONNECTION_FAILED))
                        .given(stockClient)
                        .validateStockQuantity(anyLong(),anyLong());

        // when // then
        Assertions.assertThatThrownBy(() -> orderService.buyProduct(buyRequest,now))
                .isInstanceOf(ApiConnectionFailException.class)
                .hasMessage("API 통신에 실패했습니다.");
    }

}