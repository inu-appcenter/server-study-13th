package com.example.hw3.model.product;

import com.example.hw3.domain.Product;
import com.example.hw3.domain.ProductStatus;
import lombok.Data;

@Data
public class ProductResponse {

    private String name;

    private Integer stackQuantity;

    private Integer price;

    private ProductStatus status;

    public ProductResponse(Product product) {
        this.name = product.getName();
        this.stackQuantity = product.getStackQuantity();
        this.price = product.getPrice();
        this.status = product.getStatus();
    }
}
