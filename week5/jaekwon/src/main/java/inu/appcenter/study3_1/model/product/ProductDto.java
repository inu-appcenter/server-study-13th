package inu.appcenter.study3_1.model.product;

import inu.appcenter.study3_1.domain.Product;
import lombok.Data;

@Data
public class ProductDto {

    private Long id;

    private String name;

    private int price;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
    }
}
