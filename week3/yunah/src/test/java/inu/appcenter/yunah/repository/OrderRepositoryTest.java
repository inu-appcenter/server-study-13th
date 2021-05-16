package inu.appcenter.yunah.repository;

import inu.appcenter.yunah.domain.Member;
import inu.appcenter.yunah.domain.Order;
import inu.appcenter.yunah.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@SpringBootTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Commit
    void test() {
        Member member = Member.createMember("jyajoo1020@gmail.com", "정윤아", "1111");
        memberRepository.save(member);

        Product product = Product.createProduct("햄버거", 5000, 100);

        Order order = Order.createOrder(10, member, product);
        orderRepository.save(order);
    }

    @Test
    void searchTest() {
        orderRepository.findById(1L);
    }

}