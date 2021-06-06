package inu.appcenter.yunah.domain;

import inu.appcenter.yunah.domain.status.OrderStatus;
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

    public static Order createOrder(Member member, Product product, Integer count) {
        Order order = new Order();
        order.totalPrice = count * product.getPrice();
        order.count = count;
        product.changeStockQuantity(count);
        order.status = OrderStatus.ACTIVE;
        order.member = member;
        order.product = product;
        return order;
    }

    public void deleteOrder(Order order) {
        this.status = OrderStatus.DELETE;
        this.product.cancelStockQuantity(order.getCount());
    }
}
