package com.module.order.stockproject.api;

import com.module.order.stockproject.dto.StockRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class StockClient {
    private static final String URL = "127.0.0.1";

    public void validateStockQuantity(long stockId, long quantity) {
        StockRequest request = StockRequest.builder()
                .stockId(stockId)
                .requireAmount(quantity)
                .build();
        // TODO: 예외 처리하기
        WebClient.create()
                .post()
                .uri(URL)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public Integer getStockPrice(long stockId){
        return WebClient.create()
                .get()
                .uri(URL +"/" + stockId)
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
    }

}
