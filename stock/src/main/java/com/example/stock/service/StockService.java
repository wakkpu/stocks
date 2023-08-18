package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.dto.StockDto;
import com.example.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;

    // 존재하지 않는 상품을 요청했을 때 예외 발생
    public StockDto getStock(Long stockId) {

        Optional<Stock> stock = stockRepository.findById(stockId);
        if(stock.isEmpty()) {
            throw new RuntimeException("NotExistException"); // Temporary Code. 커스텀 예외 만들 것
        }
        return StockDto.entityToDto(stock.get());
    }

    public void isSatisfyRequire(StockDto stockDto, int requireAmount) {
        if(stockDto.getAmount() < requireAmount) {
            throw new RuntimeException("UnsatisfyingException");
        }
    }
}
