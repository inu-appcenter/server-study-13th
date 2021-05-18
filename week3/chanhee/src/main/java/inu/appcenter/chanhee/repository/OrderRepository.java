package inu.appcenter.chanhee.repository;

import inu.appcenter.chanhee.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // 주문과 상품을 같이 조회
    @Query("select distinct o from Order o join fetch o.product where o.id=:orderId")
    Optional<Order> findOrderWithProduct(@Param("orderId") Long orderId) ;
}
