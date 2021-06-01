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
    public static Product registerProduct(String name, Integer stockQuantity, Integer price) {

        Product product = new Product();
        product.name = name;
        product.stockQuantity = stockQuantity;
        product.status = ProductStatus.REGISTER;
        product.price = price;

        return product;
    }

    // 상품 수정
    public void updateProduct(Integer price, Integer stockQuantity) {
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    // 상품 삭제 ( status만 변경 )
    public void deleteProduct(ProductStatus status) {
        this.status = status.DELETE;
    }

    // 상품 주문 시 해당 수량 만큼 재고량 감소
    public void changeStockQuantity(Integer count){
        this.stockQuantity -= count;
    }

    // 상품 주문취소 시 주문 했던 수량만큼 재고량 원상복구
    public void cancleStockQuantity(int count) {
        this.stockQuantity += count;
    }
}
