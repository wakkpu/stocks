package com.example.stock.controller;

import com.example.stock.dto.StockDto;
import com.example.stock.dto.ValidateDto;
import com.example.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    // 사려는 물건이 원하는 갯수 이상인지 확인하는 api
    // 갯수가 모자라면 400
    // 아예 존재하지 않는 물품이면 404
    /**
     * Read요청 시 POST vs. GET
     * 일반적으로, REST API에서 Read 연산을 할때 Get을 사용하는데,
     * 구글링을 해보니, 보안상의 이유로(사실 따지고보면 POST도 보안상으로 완벽한 건 아니다)
     * Read를 할때 POST를 쓰는 경우도 있다고 한다.
     */
    @PostMapping("/")
    public ResponseEntity<?> validateStock(@RequestBody ValidateDto validateDto) {
        /**
         * Controller의 코드 부분을 비즈니스 로직으로 봐서 Service단으로 내려야 할까?
         * vs.
         * 이정도는 비즈니스 로직이 아니라고 생각해서 Controller에 두자
         *
         * -> Custom Exception이나 Enum 등으로 규약을 만들어서 해결할 수도 있다
         * vs.
         * 커스텀 규약은 최대한 지양하는 것이 맞다.
         *
         * 나름의 해결) @ControlerAdvice의 Global Exception Handler 통해서 Exception 처리하면 된다.
         * Controller단에서 try-catch할 필요 없음.
         */
        StockDto stock = null;
        try {
            stock = stockService.getStock(validateDto.getStockId());
        } catch (RuntimeException e) {
            // return 400
        }

        boolean flag = stockService.isSatisfyRequire(stock, validateDto.getRequireAmount());

        if(flag) {
            // return 200
        }  else {
            // return 400
        }
    }
}
