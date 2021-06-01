package inu.appcenter.study3_1.repository;

import inu.appcenter.study3_1.domain.Member;
import inu.appcenter.study3_1.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    //  주문과 상품 같이 조회
    @Query("select o from Order o left join fetch o.product where o.id =:orderId")
    Optional<Order> findWithProductById(@Param("orderId") Long orderId);
}
