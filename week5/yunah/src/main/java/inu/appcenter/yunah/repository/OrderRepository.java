package inu.appcenter.yunah.repository;

import inu.appcenter.yunah.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
