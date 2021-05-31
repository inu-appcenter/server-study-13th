package inu.appcenter.yunah.service;

import inu.appcenter.yunah.domain.Member;
import inu.appcenter.yunah.domain.Order;
import inu.appcenter.yunah.domain.Product;
import inu.appcenter.yunah.exception.MemberException;
import inu.appcenter.yunah.exception.OrderException;
import inu.appcenter.yunah.exception.ProductException;
import inu.appcenter.yunah.model.order.OrderSaveRequest;
import inu.appcenter.yunah.repository.MemberRepository;
import inu.appcenter.yunah.repository.OrderRepository;
import inu.appcenter.yunah.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void saveOrder(Long memberId, OrderSaveRequest orderSaveRequest) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));
        Product product = productRepository.findById(orderSaveRequest.getProductId())
                .orElseThrow(() -> new ProductException("존재하지 않는 상품입니다."));
        Order order = Order.createOrder(member, product, orderSaveRequest.getCount());
        orderRepository.save(order);
    }

    @Transactional
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException("존재하지 않는 주문입니다."));
        order.deleteOrder(order);
    }
}
