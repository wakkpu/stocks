package com.module.order.stockproject.repository;

import com.module.order.stockproject.entity.Order;
import com.module.order.stockproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 주문이란, 재고 확인, 잔고 확인, 재고 차감, 잔고 차감 등의 시나리오인데,
 * 우리쪽에서 재고 확인, 재고 차감에 관한 부분까지 가져가는지,
 * 아니면 재고 도메인에 관련된 것이니 반대편 페어쪽에서 가져갈 것인지
 * 개발 범위를 어떻게 해야할지 결정이 안 선다
 *
 * -> 창효님 의견 : stock 갯수 확인 요청해서 받고, 그러고 나서 우리쪽에서 Order 다 처리하면 될 듯?
 *
 * => [재고 확인 -> 잔고 확인 -> 재고 차감 -> 잔고 차감] 까지가 트랜잭션 아닌가?
 * => 재고 확인 -> [잔고 확인 -> 재고 차감 -> 잔고 차감]은 트랜잭션이 아닌거 아닌가?
 * => 재고 확인, 잔고 확인은 DB 조회지, 상태 변경이 아니므로 트랜잭션에 포함될 게 아닌 것 같다. [재고 확인, 잔고 확인]만 트랜잭션 처리하면 될 듯
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
    // C, R
    // TODO 성훈님이 공부하고 알려주신다고 함
    List<Order> findOrdersByUser(User user); // 사용자 통해 주문 목록 조회

    List<Order> findOrdersByUser_Id(Long id);
}
