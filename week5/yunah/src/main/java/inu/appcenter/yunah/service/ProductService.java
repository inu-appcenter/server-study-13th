package inu.appcenter.yunah.service;

import inu.appcenter.yunah.domain.Product;
import inu.appcenter.yunah.exception.ProductException;
import inu.appcenter.yunah.model.product.ProductSaveRequest;
import inu.appcenter.yunah.model.product.ProductUpdateRequest;
import inu.appcenter.yunah.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public void saveProduct(ProductSaveRequest productSaveRequest) {
        Product product = Product.createProduct(productSaveRequest.getName(), productSaveRequest.getPrice(), productSaveRequest.getStockQuantity());
        productRepository.save(product);
    }

    @Transactional
    public void updateProduct(Long productId, ProductUpdateRequest productUpdateRequest) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException("존재하지 않는 상품입니다."));
        product.updateProduct(productUpdateRequest.getName(), productUpdateRequest.getStockQuantity());
    }

    @Transactional
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException("존재하지 않는 상품입니다."));
        product.deleteProduct(product);
    }

    public List<Product> findAllProduct() {

        List<Product> products = productRepository.findAll();
        return products;
    }

    public Product findProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException("존재하지 않는 상품입니다."));
        return product;
    }
}
