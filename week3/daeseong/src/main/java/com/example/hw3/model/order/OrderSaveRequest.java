package com.example.hw3.model.order;

import lombok.Data;

@Data
public class OrderSaveRequest {

    private Long productId;
    private Integer count;
}
