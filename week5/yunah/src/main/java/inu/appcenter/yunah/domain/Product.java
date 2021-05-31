package inu.appcenter.yunah.domain;

import inu.appcenter.yunah.domain.status.ProductStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    public static Product createProduct(String name, int price, int stockQuantity) {
        Product product = new Product();
        product.name = name;
        product.price = price;
        product.stockQuantity = stockQuantity;
        product.status = ProductStatus.ACTIVE;
        return product;
    }

    public void updateProduct(String name, int stockQuantity) {
        this.name = name;
        this.stockQuantity = stockQuantity;
    }

    public void deleteProduct(Product product) {
        this.status = ProductStatus.DELETE;
    }

    public void changeStockQuantity(Integer count) {
        this.stockQuantity -= count;
    }

    public void cancelStockQuantity(int count) {
        this.stockQuantity += count;
    }
}
