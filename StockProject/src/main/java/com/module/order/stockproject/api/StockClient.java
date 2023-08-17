package com.module.order.stockproject.api;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class StockClient {
    private static final String URL = "127.0.0.1";

    public void validateStockQuantity(long stockId, long quantity) {
        WebClient.create()
            .get()
            .uri(makeUrl(stockId, quantity))
            .retrieve()
//            .onStatus(HttpStatus::isError, res -> res.bodyToMono(String.class)
//                    .flatMap(error -> Mono.error(
//                            new ApiConnectionFailException(API_CONNECTION_FAILED)
//                            )
//                    )
//            )
            .bodyToMono(Boolean.class)
            .block();
    }

    public long getStockPrice(long stockId){
        return 1;
    }

    private String makeUrl(long stockId, long quantity){
        return URL + "asdasd";
    }
}
