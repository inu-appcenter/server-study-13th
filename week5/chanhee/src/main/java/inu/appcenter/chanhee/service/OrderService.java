package inu.appcenter.chanhee.service;

import inu.appcenter.chanhee.domain.member.Member;
import inu.appcenter.chanhee.domain.order.Order;
import inu.appcenter.chanhee.domain.product.Product;
import inu.appcenter.chanhee.exception.MemberException;
import inu.appcenter.chanhee.exception.OrderException;
import inu.appcenter.chanhee.exception.ProductException;
import inu.appcenter.chanhee.model.order.OrderSaveRequest;
import inu.appcenter.chanhee.repository.MemberRepository;
import inu.appcenter.chanhee.repository.OrderRepository;
import inu.appcenter.chanhee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    // 주문 생성
    @Transactional
    public Long createOrder(Long memberId, OrderSaveRequest orderSaveRequest) {

        // 회원 확인
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회웝입니다."));

        // 주문할 제품이 존재하는지 확인
        Product findProduct = productRepository.findById(orderSaveRequest.getProductId())
                .orElseThrow(() -> new ProductException("존재하지 않는 상품입니다."));

        Order order = Order.createOrder(findMember, findProduct, orderSaveRequest.getCount());
        Order savedOrder = orderRepository.save(order);

        return savedOrder.getId();
    }

    // 주문 취소
    @Transactional
    public void deleteOrder(Long orderId) {

        // 주문 확인
        Order findOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException("존재하지 않는 주문입니다."));

        findOrder.cancleOrder(findOrder.getStatus(), findOrder.getCount());
    }
}
