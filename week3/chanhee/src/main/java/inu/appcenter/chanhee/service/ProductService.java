package inu.appcenter.chanhee.service;

import inu.appcenter.chanhee.domain.product.Product;
import inu.appcenter.chanhee.exception.ProductException;
import inu.appcenter.chanhee.model.product.ProductSaveRequest;
import inu.appcenter.chanhee.model.product.ProductUpdateRequest;
import inu.appcenter.chanhee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    // 상품 등록
    @Transactional
    public Long saveProduct(ProductSaveRequest productSaveRequest) {

        Product product = Product.registerProduct(productSaveRequest.getName(),
                                                  productSaveRequest.getStockQuantity(),
                                                  productSaveRequest.getPrice());

        Product savedProduct = productRepository.save(product);
        return savedProduct.getId();
    }

    // 상품 수정
    @Transactional
    public void updateProduct(Long productId, ProductUpdateRequest productUpdateRequest) {

        // 상품 id 조회
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException("존재하지 않는 상품입니다."));

        // 상품 id가 존재한다면
        product.updateProduct(productUpdateRequest.getStockQuantity(), productUpdateRequest.getPrice());
    }

    // 상품 삭제
    @Transactional
    public void deleteProduct(Long productId) {

        // 상품 id 조회
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException("존재하지 않는 상품입니다."));

        // 상품 id가 존재한다면
        product.deleteProduct(product.getStatus());
    }

    // 상품 리스트 조회
    public List<Product> findAll() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    // 상품 ID 조회
    public Product findProductById(Long productId) {

        // 상품 id 조회
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException("존재하지 않는 상품입니다."));

        // 상품 id가 존재한다면
        return product;
    }
}
