package inu.appcenter.chanhee.model.order;

import inu.appcenter.chanhee.domain.order.Order;
import inu.appcenter.chanhee.domain.order.OrderStatus;
import inu.appcenter.chanhee.model.product.ProductResponse;
import lombok.Data;


@Data
public class OrderDto {

    private Long orderId;

    private int totalPrice;

    private int count;

    private OrderStatus status;

    private ProductResponse products;

    public OrderDto(Order order) {
        this.orderId = order.getId();
        this.totalPrice = order.getTotalPrice();
        this.count = order.getCount();
        this.status = order.getStatus();
        this.products = new ProductResponse(order.getProduct());
    }
}
