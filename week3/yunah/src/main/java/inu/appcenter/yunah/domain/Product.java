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

    public void changeStockQuantity(Integer count) {
        this.stockQuantity -= count;
    }

    public void cancelStockQuantity(Integer count) {
        this.stockQuantity += count;
    }

    public void changeProduct(Integer price, Integer stockQuantity) {
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public void deleteProduct(Product product) {
        product.status = ProductStatus.DELETED;
    }
}
