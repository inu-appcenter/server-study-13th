package com.example.hw5.controller;

import com.example.hw5.config.security.LoginMember;
import com.example.hw5.model.order.OrderSaveRequest;
import com.example.hw5.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<Void> saveOrder(@RequestBody @Valid OrderSaveRequest orderSaveRequest, @LoginMember User user) {

        orderService.saveOrder(orderSaveRequest, user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {

        orderService.deleteOrder(orderId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
