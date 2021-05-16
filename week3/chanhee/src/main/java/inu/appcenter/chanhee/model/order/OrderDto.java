package inu.appcenter.chanhee.model.order;

import inu.appcenter.chanhee.domain.order.Order;
import inu.appcenter.chanhee.domain.order.OrderStatus;
import lombok.Data;

@Data
public class OrderDto {

    private Long orderId;

    private Long productId;

    private int totalPrice;

    private int count;

    private OrderStatus status;

    public OrderDto(Order order) {
        this.orderId = order.getId();
        this.productId = order.getProduct().getId();
        this.totalPrice = order.getTotalPrice();
        this.count = order.getCount();
        this.status = order.getStatus();
    }
}
