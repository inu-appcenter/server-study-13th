package inu.appcenter.chanhee.model.product;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductSaveRequest {

    @NotBlank
    private String name;

    @NotNull
    private Integer stockQuantity;

    @NotNull
    private Integer price;

}
