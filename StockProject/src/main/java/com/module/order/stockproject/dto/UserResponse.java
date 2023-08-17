package com.module.order.stockproject.dto;

import com.module.order.stockproject.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String userName;
    private long balance;

    @Builder
    private UserResponse(Long id, String userName, long balance) {
        this.id = id;
        this.userName = userName;
        this.balance = balance;
    }

    public static UserResponse fromEntity(User user){
        return UserResponse.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .balance(user.getBalance())
                .build();
    }

}
