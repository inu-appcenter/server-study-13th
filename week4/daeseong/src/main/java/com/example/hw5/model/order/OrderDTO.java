package com.example.hw5.model.order;

import com.example.hw5.domain.Order;
import lombok.Data;

@Data
public class OrderDTO {

    private int totalPrice;
    private int count;

    public OrderDTO(Order order) {
        this.totalPrice = order.getTotalPrice();
        this.count = order.getCount();
    }
}
