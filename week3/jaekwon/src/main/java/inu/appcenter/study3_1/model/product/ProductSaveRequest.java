package inu.appcenter.study3_1.model.product;

import lombok.Data;

@Data
public class ProductSaveRequest {

    private String name;
    private int stockQuantity;
    private int price;
}
