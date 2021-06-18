package com.example.hw5.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    private int totalPrice;

    private int count;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)  // LAZY -> 지연 로딩, EAGER -> product나 member도 같이 조회
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public static Order createOrder(int count, Member member, Product product) {

        product.changeStockQuantity(count);

        Order order = new Order();

        order.totalPrice = count * product.getPrice();
        order.count = count;
        order.status = OrderStatus.ORDERED;
        order.member = member;
        order.product = product;

        return order;
    }

    public void delete() {

        product.returnStockQuantity(this.getCount());

        this.status = OrderStatus.CANCELED;
    }
}
