package inu.appcenter.chanhee.repository;

import inu.appcenter.chanhee.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
