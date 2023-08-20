package com.module.order.stockproject.service;

import com.module.order.stockproject.dto.OrderResponse;
import com.module.order.stockproject.entity.User;
import com.module.order.stockproject.repository.OrderRepository;
import com.module.order.stockproject.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest

class OrderRepoServiceTest {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderRepoService orderRepoService;
    @Autowired
    UserRepository userRepository;

    @DisplayName("유저, 재고 아이디, 주문 시간을 입력받아 주문을 생성합니다.")
    @Test
    void makeOrder(){
        // given
        User user = User.builder()
                .userName("test1")
                .build();
        userRepository.save(user);

        long stockId = 1L;
        LocalDateTime now = LocalDateTime.now();

        // when
        OrderResponse orderResponse = orderRepoService.makeOrder(user, stockId, now);

        // then
        assertThat(orderResponse.getId()).isNotNull();
        assertThat(orderResponse).extracting(
                        "stockId","orderTime"
                )
                .containsExactlyInAnyOrder(stockId,now);
    }

}