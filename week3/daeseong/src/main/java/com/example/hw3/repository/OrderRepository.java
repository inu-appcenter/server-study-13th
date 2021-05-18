package com.example.hw3.repository;

import com.example.hw3.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select distinct o from Order o join fetch o.product where o.id=:orderId")
    Optional<Order> findWithProduct(@Param("orderId") Long orderId);
}