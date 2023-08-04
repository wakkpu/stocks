package com.module.order.stockproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @JoinColumn(name = "id")
    @Column(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    private User user;

    @Column(nullable = false)
    private LocalDateTime orderTime;
}
