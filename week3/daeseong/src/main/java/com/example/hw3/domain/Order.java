package com.example.hw3.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Enumerated(EnumType.STRING)  // 꼭 String으로
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)  // LAZY -> 지연 로딩, EAGER -> product나 member도 같이 조회
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public static Order createOrder(int count, Member member, Product product) {
        Order order = new Order();

        order.count = count;
        product.changeStockQuantity(count);
        order.totalPrice = count * product.getPrice();
        order.member = member;
        order.product = product;
        order.status = OrderStatus.ORDERED;

        return order;
    }

    public void delete() {
        product.returnStockQuantity(this.getCount());
        this.status = OrderStatus.CANCELED;
    }
}
