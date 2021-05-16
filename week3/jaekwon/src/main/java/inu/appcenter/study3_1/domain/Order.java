package inu.appcenter.study3_1.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "orders")
public class Order {

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

    public static Order createOrder(int count,Member member, Product product){
        Order order = new Order();
        order.count = count;
        product.changeStockQuantity(count);
        order.totalPrice = count * product.getPrice();
        order.status = OrderStatus.ACTIVATE;
        order.member = member;
        order.product = product;
        return order;
    }

    public void deleteOrder(int count) {
        this.status = OrderStatus.DELETED;
        this.product.recoveryStockQuantity(count);
    }
}
