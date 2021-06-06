package inu.appcenter.yunah.model.product;

import inu.appcenter.yunah.domain.Product;
import inu.appcenter.yunah.domain.status.ProductStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductResponse {

    private Long productId;
    private String name;
    private Integer price;
    private Integer stockQuantity;
    private ProductStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ProductResponse from(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.productId = product.getId();
        productResponse.name = product.getName();
        productResponse.price = product.getPrice();
        productResponse.status = product.getStatus();
        productResponse.stockQuantity = product.getStockQuantity();
        productResponse.createdAt = product.getCreatedAt();
        productResponse.updatedAt = product.getUpdatedAt();
        return productResponse;
    }

}
