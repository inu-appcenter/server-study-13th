package inu.appcenter.yunah.model.product;

import inu.appcenter.yunah.domain.Product;
import inu.appcenter.yunah.domain.status.ProductStatus;
import lombok.Data;

@Data
public class ProductResponse {

    private Long productId;

    private String name;

    private Integer price;

    private Integer stockQuantity;

    private ProductStatus status;

    public ProductResponse(Product product) {

        this.productId = product.getId();

        this.name = product.getName();

        this.price = product.getPrice();

        this.stockQuantity = product.getStockQuantity();

        this.status = product.getStatus();
    }
}
