package com.module.order.stockproject.entity;

import com.module.order.stockproject.exception.InsufficientBalanceException;
import com.module.order.stockproject.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class UserTest {

    @DisplayName("유저를 생성했을 때 기본 잔액은 0원 입니다.")
    @Test
    void basicBalance(){
        User user = User.builder()
                .userName("test1")
                .build();
        Assertions.assertThat(user.getBalance()).isEqualTo(0);
    }

    @DisplayName("유저는 자신의 잔액 이하의 물건을 구매할 수 있습니다.")
    @Test
    void canBuy(){
        int userBalance = 10000;
        int buyPrice = 10000;
        User user = User.builder()
                .userName("test1")
                .balance(userBalance)
                .build();

        user.canBuy(buyPrice);
    }

    @DisplayName("유저는 자신의 잔액을 초과하는 물건을 구매할 수 없습니다.")
    @Test
    void canBuy2(){
        int userBalance = 10000;
        int buyPrice = 11000;
        User user = User.builder()
                .userName("test1")
                .balance(userBalance)
                .build();

        assertThatThrownBy(() -> user.canBuy(buyPrice))
                        .isInstanceOf(InsufficientBalanceException.class)
                        .hasMessage("잔액이 부족합니다.");
    }



}