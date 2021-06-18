package com.example.hw5.model.product;

import com.example.hw5.domain.ProductStatus;
import com.example.hw5.domain.Product;
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
