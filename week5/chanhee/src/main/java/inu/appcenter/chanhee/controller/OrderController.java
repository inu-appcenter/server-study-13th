package inu.appcenter.chanhee.controller;

import inu.appcenter.chanhee.config.security.LoginMember;
import inu.appcenter.chanhee.model.order.OrderSaveRequest;
import inu.appcenter.chanhee.service.OrderService;
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

    // 주문 생성
    @PostMapping("/orders")
    public ResponseEntity createOrder(@LoginMember User user,
                                      @Valid @RequestBody OrderSaveRequest orderSaveRequest) {

        Long memberId = Long.parseLong(user.getUsername());
        Long orderId = orderService.createOrder(memberId, orderSaveRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }

    // 주문 취소
    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity deleteOrder(@PathVariable Long orderId) {

        orderService.deleteOrder(orderId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
