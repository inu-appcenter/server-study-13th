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
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    // 주문 생성
    @Transactional
   public Long createOrder(Long memberId, OrderSaveRequest orderSaveRequest) {

       // 회원 id 확인
       Member findMember = memberRepository.findById(memberId)
               .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));

       // 상품 id 확인
        Product findProduct = productRepository.findById(orderSaveRequest.getProductId())
                .orElseThrow(() -> new ProductException("상품이 존재하지 않습니다."));

       // 회원 id와 상품 id 두 데이터가 모두 존재한다면
        Order order = Order.createOrder(orderSaveRequest.getCount(), findMember, findProduct);
        Order savedOrder = orderRepository.save(order);

        return savedOrder.getId();
    }

    // 주문 취소
    @Transactional
    public void deleteOrder(Long orderId) {

        // 주문 id 확인
        Order order = orderRepository.findOrderWithProduct(orderId)
                .orElseThrow(() -> new OrderException("해당 주문건이 없습니다."));

        // 주문 id가 존재한다면
        order.deleteOrder(order.getStatus(), order.getCount());
    }
}
