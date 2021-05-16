package inu.appcenter.study3_1.controller;

import inu.appcenter.study3_1.model.order.OrderSaveRequest;
import inu.appcenter.study3_1.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // 주문 생성
    @PostMapping("/members/{memberId}/orders")
    public ResponseEntity saveOrder(@PathVariable Long memberId,
                                    @RequestBody @Valid OrderSaveRequest orderSaveRequest){
        Long saveOrderId = orderService.createOrder(memberId, orderSaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveOrderId);
    }

    //  주문 취소
    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity deleteOrder(@PathVariable Long orderId){
        orderService.deleteOrder(orderId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
