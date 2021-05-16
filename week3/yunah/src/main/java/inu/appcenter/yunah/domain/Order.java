package inu.appcenter.yunah.domain;

import inu.appcenter.yunah.domain.status.OrderStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "orders") //  Database에 생성될 table의 이름 지정
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")

    private Long id;

    private int totalPrice;

    private int count;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // 주문(order) : 회원(member) = N : 1
    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩. (OneToMany에서는 LAZY가 default, ToOne일 경우에는 LAZY로 설정해주기.)
    @JoinColumn(name = "member_id")    // 외래키
    private Member member;

    // 주문(order) : 상품(product) : 1 : 1 ( 단방향 )
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)  // EAGER (product까지 같이 가져온다. 사용 X, 성능 최적화를 할 수 없다.)
    @JoinColumn(name = "product_id")   // 외래키
    private Product product;


    public static Order createOrder(Integer count, Member member, Product product) {

        Order order = new Order();
        order.count = count;
        product.changeStockQuantity(count);

        order.totalPrice = count * product.getPrice();
        order.member = member;
        order.product = product;
        order.status = OrderStatus.ACTIVE;
        return order;
    }

    public static void cancelOrder(Order order) {

        order.status = OrderStatus.DELETED;
        order.product.cancelStockQuantity(order.count);
    }
}
