package inu.appcenter.study3_1.model.product;

import lombok.Data;

@Data
public class ProductUpdateRequest {

    private int stockQuantity;
    private int price;
}
