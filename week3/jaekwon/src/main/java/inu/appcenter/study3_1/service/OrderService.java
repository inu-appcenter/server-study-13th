package inu.appcenter.study3_1.service;

import inu.appcenter.study3_1.domain.Member;
import inu.appcenter.study3_1.domain.Order;
import inu.appcenter.study3_1.domain.Product;
import inu.appcenter.study3_1.exception.MemberException;
import inu.appcenter.study3_1.exception.OrderException;
import inu.appcenter.study3_1.exception.ProductException;
import inu.appcenter.study3_1.model.order.OrderSaveRequest;
import inu.appcenter.study3_1.repository.MemberRepository;
import inu.appcenter.study3_1.repository.OrderRepository;
import inu.appcenter.study3_1.repository.ProductRepository;
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

    @Transactional
    public Long createOrder(Long memberId, OrderSaveRequest orderSaveRequest){
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));

        Product findProduct = productRepository.findById(orderSaveRequest.getProductId())
                .orElseThrow(() -> new ProductException("존재하지 않는 상품입니다."));

        Order order = Order.createOrder(orderSaveRequest.getCount(), findMember, findProduct);
        Order savedOrder = orderRepository.save(order);

        return savedOrder.getId();
    }

    @Transactional
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findWithProductById(orderId)
                .orElseThrow(() -> new OrderException("존재하지 않는 주문입니다."));
        order.deleteOrder(order.getCount());
    }
}
