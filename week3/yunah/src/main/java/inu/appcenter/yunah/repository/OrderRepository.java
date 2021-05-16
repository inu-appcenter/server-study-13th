package inu.appcenter.yunah.repository;

import inu.appcenter.yunah.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // 주문을 조회할 때 상품도 함께 조회
    @Query("select o from Order o join fetch o.product where o.id = :orderId")
    Optional<Order> findWithProductById(Long orderId);
}
