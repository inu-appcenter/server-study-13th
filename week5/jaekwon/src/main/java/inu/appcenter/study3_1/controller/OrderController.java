package inu.appcenter.study3_1.controller;

import inu.appcenter.study3_1.config.security.LoginMember;
import inu.appcenter.study3_1.model.order.OrderSaveRequest;
import inu.appcenter.study3_1.service.OrderService;
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
    public ResponseEntity saveOrder(@LoginMember User user,
                                    @RequestBody @Valid OrderSaveRequest orderSaveRequest){
        Long memberId = Long.valueOf(user.getUsername());
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
