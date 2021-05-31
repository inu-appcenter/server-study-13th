package inu.appcenter.chanhee.domain.order;

import inu.appcenter.chanhee.domain.BaseEntity;
import inu.appcenter.chanhee.domain.member.Member;
import inu.appcenter.chanhee.domain.product.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    private int totalPrice;

    private int count;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    // 주문 생성
    public static Order createOrder(Member member, Product product, Integer count) {
        Order order = new Order();
        order.member = member;
        order.product = product;
        order.count = count;
        order.totalPrice = count * product.getPrice();
        product.changeStockQuantity(count);
        order.status = OrderStatus.PROCEED;

        return order;
    }

    // 주문 취소
    public void cancleOrder(OrderStatus status, int count) {
        this.status = status.CANCLE;
        product.cancleStockQuantity(count);
    }
}
