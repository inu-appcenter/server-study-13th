package inu.appcenter.yunah.model.product;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProductSaveRequest {

    @NotBlank
    private String name;

    private int price;

    private int stockQuantity;
}
