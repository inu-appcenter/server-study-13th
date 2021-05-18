package inu.appcenter.chanhee.model.product;

import lombok.Data;

@Data
public class ProductUpdateRequest {

    // 상품 수량과 가격만 변경 가능
    private int stockQuantity;

    private int price;
}
