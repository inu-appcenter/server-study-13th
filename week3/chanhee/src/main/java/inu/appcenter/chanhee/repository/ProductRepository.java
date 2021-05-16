package inu.appcenter.chanhee.repository;

import inu.appcenter.chanhee.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
