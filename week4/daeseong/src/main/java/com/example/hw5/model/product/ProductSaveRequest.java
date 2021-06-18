package com.example.hw5.model.product;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductSaveRequest {

    @NotBlank
    private String name;

    @NotNull
    private Integer stackQuantity;

    @NotNull
    private Integer price;
}
