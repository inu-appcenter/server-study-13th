package inu.appcenter.study3_1.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String name;

    private int stockQuantity;

    private int price;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    public static Product createProduct(String name, int stockQuantity, int price){
        Product product = new Product();
        product.name = name;
        product.stockQuantity = stockQuantity;
        product.price = price;
        product.status = ProductStatus.ACTIVE;
        return product;
    }

    public void changeStockQuantity(int count) { this.stockQuantity -= count; }


    public void updateProduct(int stockQuantity, int price) {
        this.stockQuantity = stockQuantity;
        this.price = price;
    }

    public void delete() {
        this.status = ProductStatus.DELETED;
    }

    public void recoveryStockQuantity(int count) { this.stockQuantity += count; }
}
