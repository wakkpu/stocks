package com.module.order.stockproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

//    관성적으로 Wrapper class 사용했었는데,
//    잔고가 null원이 될 필요도 없으니 원시 타입으로 사용하는 게 타당할 거 같음.
//    @Column(nullable = false)
//    private Long balance;
    @ColumnDefault("0")
    private long balance;
}
