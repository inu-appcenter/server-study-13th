package inu.appcenter.yunah.model.order;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderSaveRequest {

    @NotNull
    private Long productId;
    @NotNull
    private Integer count;
}
