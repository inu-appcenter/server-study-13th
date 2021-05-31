package inu.appcenter.chanhee.model.product;

import lombok.Data;

@Data
public class ProductUpdateRequest {

    // 수량과 가격만 수정가능
    private Integer price;

    private Integer stockQuantity;
}
