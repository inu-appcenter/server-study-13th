package inu.appcenter.chanhee.controller;

import inu.appcenter.chanhee.model.order.OrderSaveRequest;
import inu.appcenter.chanhee.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // 주문 생성
    @PostMapping("/members/{memberId}/orders")
    public ResponseEntity saveOrder(@PathVariable Long memberId,
                                    @RequestBody OrderSaveRequest orderSaveRequest) {

        Long savedOrderId = orderService.createOrder(memberId, orderSaveRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrderId);
    }

    // 주문 취소
    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity deleteOrder(@PathVariable Long orderId) {

        orderService.deleteOrder(orderId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
