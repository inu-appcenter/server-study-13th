package inu.appcenter.yunah.model.product;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductSaveRequest {

    @NotBlank
    private String name;

    @NotNull
    private int price;
    @NotNull
    private int stockQuantity;

}
