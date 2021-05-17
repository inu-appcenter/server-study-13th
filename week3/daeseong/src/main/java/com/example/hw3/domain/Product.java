package com.example.hw3.domain;

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

    private int stackQuantity;

    private int price;

    @Enumerated(EnumType.STRING)  // 꼭 String으로
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
        this.stackQuantity -= count;
    }

    public void returnStockQuantity(int count){
        this.stackQuantity += count;
    }

    public void patch(int stackQuantity, int price) {
        this.stackQuantity = stackQuantity;
        this.price = price;
    }

    public void delete(){
        this.status = ProductStatus.SOLDOUT;
    }
}
