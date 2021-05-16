package inu.appcenter.yunah.model.order;

import lombok.Data;

@Data
public class OrderSaveRequest {

    private Long productId;

    private Integer count;
}
