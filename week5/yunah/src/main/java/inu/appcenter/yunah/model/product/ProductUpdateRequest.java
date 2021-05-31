package inu.appcenter.yunah.model.product;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductUpdateRequest {

    @NotBlank
    private String name;
    @NotNull
    private int stockQuantity;
}
