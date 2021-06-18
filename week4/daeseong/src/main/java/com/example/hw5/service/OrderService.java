package com.example.hw5.service;

import com.example.hw5.domain.Member;
import com.example.hw5.domain.Order;
import com.example.hw5.domain.Product;
import com.example.hw5.exception.MemberException;
import com.example.hw5.exception.OrderException;
import com.example.hw5.exception.ProductException;
import com.example.hw5.model.order.OrderSaveRequest;
import com.example.hw5.repository.MemberRepository;
import com.example.hw5.repository.OrderRepository;
import com.example.hw5.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public void saveOrder(OrderSaveRequest orderSaveRequest, User user) {

        Member member = memberRepository.findById(Long.parseLong(user.getUsername()))
                .orElseThrow(() -> new MemberException("No Member Exists"));

        Product product = productRepository.findById(orderSaveRequest.getProductId())
                .orElseThrow(() -> new ProductException("No Product Exists"));

        Order order = Order.createOrder(orderSaveRequest.getCount(), member, product);

        orderRepository.save(order);
    }

    @Transactional
    public void deleteOrder(Long orderId) {

        Order order = orderRepository.findOrderByIdWithProduct(orderId)
                .orElseThrow(() -> new OrderException("No Order Exists"));

        order.delete();
    }
}
