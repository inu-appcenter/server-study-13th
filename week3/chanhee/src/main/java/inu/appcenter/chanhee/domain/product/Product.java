package inu.appcenter.chanhee.domain.product;

import inu.appcenter.chanhee.domain.BaseEntity;
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

    private int stockQuantity;

    private int price;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    // 상품 등록
    public static Product registerProduct(String name, int stockQuantity, int price) {
        Product product = new Product();
        product.name = name;
        product.stockQuantity = stockQuantity;
        product.price = price;
        product.status = ProductStatus.REGISTER;
        return product;
    }

    // 상품 수정
    public void updateProduct(int stockQuantity, int price) {
        this.stockQuantity = stockQuantity;
        this.price = price;
    }

    // 상품 삭제
    public void deleteProduct(ProductStatus status) {
        this.status = status.DELETE;
    }

    // 상품 주문시 주문 수량 만큼 해당 상품 재고 감소
    public void changeStockQuantity(Integer count) {
        this.stockQuantity -= count;
    }

    // 주문 취소시 취소된 주문 수량만큼 상품 수량 원래대로 증가
    public void cancleStockQuantity(int count) {
        this.stockQuantity += count;
    }
}
