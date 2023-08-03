package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;

    public Stock findStockById(Long stockId){
        return stockRepository.findById(stockId).orElseThrow(() -> new NOT_EXIST_EXCEPTION("요청한 상품이 존재하지 않습니다."));
    }

}
