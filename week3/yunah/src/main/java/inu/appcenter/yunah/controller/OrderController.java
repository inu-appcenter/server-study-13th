package inu.appcenter.yunah.controller;

import inu.appcenter.yunah.model.order.OrderSaveRequest;
import inu.appcenter.yunah.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController {

    public final OrderService orderService;

    /*
    주문 생성
     */
    @PostMapping("/members/{memberId}/orders")
    public ResponseEntity saveOrder(@PathVariable Long memberId, @RequestBody OrderSaveRequest orderSaveRequest) {

        orderService.saveOrder(memberId, orderSaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /*
    주문 취소
     */
    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity deleteOrder(@PathVariable Long orderId) {

        orderService.delete(orderId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
