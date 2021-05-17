package com.example.hw3.service;

import com.example.hw3.domain.Member;
import com.example.hw3.domain.Order;
import com.example.hw3.domain.Product;
import com.example.hw3.exception.MemberException;
import com.example.hw3.model.order.OrderSaveRequest;
import com.example.hw3.repository.MemberRepository;
import com.example.hw3.repository.OrderRepository;
import com.example.hw3.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Long createOrder(Long memberId, OrderSaveRequest orderSaveRequest) {
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("Not Existing Member"));

        Product findProduct = productRepository.findById(orderSaveRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("TODO"));

        Order order = Order.createOrder(orderSaveRequest.getCount(), findMember, findProduct);
        Order savedOrder = orderRepository.save(order);

        return savedOrder.getId();
    }

    @Transactional
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findWithProduct(orderId)
                .orElseThrow(RuntimeException::new);

        order.delete();
    }
}
