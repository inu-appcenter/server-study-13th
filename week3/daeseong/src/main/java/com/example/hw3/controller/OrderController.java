package com.example.hw3.controller;

import com.example.hw3.model.order.OrderSaveRequest;
import com.example.hw3.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/members/{memberId}/orders")
    public ResponseEntity saveOrder(@PathVariable Long memberId, @RequestBody @Valid OrderSaveRequest orderSaveRequest) {
        Long savedOrderId = orderService.createOrder(memberId, orderSaveRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrderId);
    }

    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}