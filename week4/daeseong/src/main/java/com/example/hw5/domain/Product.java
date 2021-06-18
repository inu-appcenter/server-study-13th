package com.example.hw5.domain;

import com.example.hw5.exception.ProductException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String name;

    private int stackQuantity;

    private int price;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    public static Product createProduct(String name, int stockQuantity, int price) {

        Product product = new Product();

        product.name = name;
        product.stackQuantity = stockQuantity;
        product.price = price;
        product.status = ProductStatus.AVAILABLE;

        return product;
    }

    public void changeStockQuantity(int count) {

        if (stackQuantity < count) {
            throw new ProductException("Out of Stock!");
        }

        this.stackQuantity -= count;
    }

    public void patch(int stackQuantity, int price) {

        this.stackQuantity = stackQuantity;
        this.price = price;
    }

    public void delete() {

        this.status = ProductStatus.UNAVAILABLE;
    }

    public void returnStockQuantity(int count) {

        this.stackQuantity += count;
    }
}
