package com.module.order.stockproject.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Orders") //Order 예약어에요
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //이 모듈시스템에서, 재고와 관련해 알수 있는 정보인 stockId를 저장
    private Long stockId;
    /*
    @JoinColumn(name = "id")
    @Column(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Stock.class)
    private Stock stock;
    */

    /**
     From JPA 2.0 spec, the defaults are:
     OneToMany: LAZY
     ManyToOne: EAGER
     ManyToMany: LAZY
     OneToOne: EAGER

     -> 이것이지만, 명시적으로 fetch type LAZY로 선언하자
     */
    @JoinColumn(name = "user_id")
//    @Column(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    private User user;

    @Column(nullable = false)
    private LocalDateTime orderTime;
}
