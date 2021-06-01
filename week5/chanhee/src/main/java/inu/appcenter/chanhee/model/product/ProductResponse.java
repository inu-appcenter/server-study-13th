package inu.appcenter.chanhee.model.product;

import inu.appcenter.chanhee.domain.product.Product;
import lombok.Data;

@Data
public class ProductResponse {

    private Long productId;

    private String name;

    private int stockQuantity;

    private int price;

    public ProductResponse(Product product) {
        this.productId = product.getId();
        this.name = product.getName();
        this.stockQuantity = product.getStockQuantity();
        this.price = product.getPrice();
    }
}
