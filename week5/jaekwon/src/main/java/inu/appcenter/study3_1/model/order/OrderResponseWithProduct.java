package inu.appcenter.study3_1.model.order;

import inu.appcenter.study3_1.domain.OrderStatus;
import lombok.Data;

@Data
public class OrderResponseWithProduct {

    private Long id;
    private int totalPrice;
    private int count;
    private OrderStatus status;
}
