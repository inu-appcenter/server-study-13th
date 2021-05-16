package inu.appcenter.study3_1.model.order;

import lombok.Data;

@Data
public class OrderSaveRequest {

    private Long productId;

    private Integer count;
}
