package com.example.hw5.repository.query;

import com.example.hw5.domain.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Product> findAll() {

        return queryFactory
                .select(QProduct.product).distinct()
                .from(QProduct.product)
                .where(QProduct.product.status.eq(ProductStatus.AVAILABLE))
                .fetch();
    }

    public Product findById(Long productId) {

        return queryFactory
                .select(QProduct.product).distinct()
                .from(QProduct.product)
                .where(QProduct.product.status.eq(ProductStatus.AVAILABLE)
                        .and(QProduct.product.id.eq(productId)))
                .fetchOne();
    }
}
