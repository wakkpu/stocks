package com.module.order.stockproject.controller;

import com.module.order.stockproject.dto.BuyRequest;
import com.module.order.stockproject.dto.OrderResponse;
import com.module.order.stockproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("buy")
    public ResponseEntity buyProduct(@RequestBody BuyRequest buyRequest){
        LocalDateTime orderDateTime = LocalDateTime.now();
        return ResponseEntity.ok().body(orderService.buyProduct(buyRequest,orderDateTime));
    }


}
