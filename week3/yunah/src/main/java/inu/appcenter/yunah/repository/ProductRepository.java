package inu.appcenter.yunah.repository;

import inu.appcenter.yunah.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
