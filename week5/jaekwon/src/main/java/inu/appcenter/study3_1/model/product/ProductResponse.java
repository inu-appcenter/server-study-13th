package inu.appcenter.study3_1.model.product;

import inu.appcenter.study3_1.domain.Product;
import inu.appcenter.study3_1.domain.ProductStatus;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class ProductResponse {

    private Long id;
    private String name;
    private int stockQuantity;
    private int price;
    private ProductStatus status;

    public ProductResponse(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.stockQuantity = product.getStockQuantity();
        this.price = product.getPrice();
        this.status = product.getStatus();
    }
}
