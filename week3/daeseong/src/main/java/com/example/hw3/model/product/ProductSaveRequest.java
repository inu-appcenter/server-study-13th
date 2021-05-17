package com.example.hw3.model.product;

import lombok.Data;

@Data
public class ProductSaveRequest {

    private String name;

    private Integer stackQuantity;

    private Integer price;
}
