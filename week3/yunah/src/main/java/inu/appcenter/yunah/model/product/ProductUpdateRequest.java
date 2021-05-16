package inu.appcenter.yunah.model.product;

import lombok.Data;

@Data
public class ProductUpdateRequest {

    private Integer price;
    private Integer stockQuantity;
}
