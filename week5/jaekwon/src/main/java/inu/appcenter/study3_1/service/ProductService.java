package inu.appcenter.study3_1.service;

import inu.appcenter.study3_1.domain.Product;
import inu.appcenter.study3_1.exception.ProductException;
import inu.appcenter.study3_1.model.product.ProductSaveRequest;
import inu.appcenter.study3_1.model.product.ProductUpdateRequest;
import inu.appcenter.study3_1.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    
    private final ProductRepository productRepository;

    @Transactional
    public void saveProduct(ProductSaveRequest productSaveRequest) {
        //상품 중복 체크
        if(productRepository.findByName(productSaveRequest.getName()).isPresent()){
            throw new ProductException("이미 등록된 상품입니다.");
        }
        Product product = Product.createProduct(productSaveRequest.getName(), productSaveRequest.getStockQuantity()
                                                , productSaveRequest.getPrice());
        productRepository.save(product);
    }

    @Transactional
    public void updateProduct(Long productId, ProductUpdateRequest productUpdateRequest) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException("없는 상품입니다."));
        product.updateProduct(productUpdateRequest.getStockQuantity(), productUpdateRequest.getPrice());
    }

    @Transactional
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException("없는 상품입니다."));
        product.delete();
    }

    public List<Product> findAll() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    public Product findById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException());
        return product;
    }
}
