package inu.appcenter.yunah.controller;

import inu.appcenter.yunah.config.security.LoginMember;
import inu.appcenter.yunah.model.order.OrderSaveRequest;
import inu.appcenter.yunah.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /*
    주문 생성
     */
    @PostMapping("/orders")
    public ResponseEntity saveOrder(@LoginMember User user, @RequestBody @Valid OrderSaveRequest orderSaveRequest) {
        String memberId = user.getUsername();
        orderService.saveOrder(Long.parseLong(memberId), orderSaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /*
    주문 취소
     */
    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
