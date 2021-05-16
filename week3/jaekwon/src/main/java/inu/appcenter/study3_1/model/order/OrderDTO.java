package inu.appcenter.study3_1.model.order;

import inu.appcenter.study3_1.domain.Order;
import lombok.Data;

@Data
public class OrderDTO {

    private Long id;
    private int totalPrice;
    private int count;

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.totalPrice = order.getTotalPrice();
        this.count = order.getCount();
    }
}
