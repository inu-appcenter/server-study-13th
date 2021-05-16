package inu.appcenter.yunah.model.order;

import inu.appcenter.yunah.domain.Order;
import lombok.Data;

@Data
public class OrderDto {

    private Long orderId;

    private int totalPrice;

    private int count;

    public OrderDto(Order order) {
        this.orderId = order.getId();
        this.totalPrice = order.getTotalPrice();
        this.count = order.getCount();
    }
}
