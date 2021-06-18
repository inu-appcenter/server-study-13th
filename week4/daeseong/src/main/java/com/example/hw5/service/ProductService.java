package com.example.hw5.service;

import com.example.hw5.domain.Product;
import com.example.hw5.exception.ProductException;
import com.example.hw5.model.product.ProductPatchRequest;
import com.example.hw5.model.product.ProductSaveRequest;
import com.example.hw5.repository.ProductRepository;
import com.example.hw5.repository.query.ProductQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductQueryRepository productQueryRepository;

    @Transactional
    public void saveProduct(ProductSaveRequest productSaveRequest) {

        if (productRepository.findByName(productSaveRequest.getName()).isPresent()) {
            throw new ProductException("Already Exists!");
        }

        Product product = Product.createProduct(productSaveRequest.getName(), productSaveRequest.getStackQuantity(), productSaveRequest.getPrice());

        productRepository.save(product);
    }

    @Transactional
    public void patchProduct(ProductPatchRequest productPatchRequest, Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException("No Product Exists!"));

        product.patch(productPatchRequest.getStackQuantity(), product.getPrice());
    }

    @Transactional
    public void deleteProduct(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException("No Product Exists!"));

        product.delete();
    }

    public List<Product> findAllProducts() {
        return productQueryRepository.findAll();
    }

    public Product findProduct(Long productId) {

        return productQueryRepository.findById(productId);
    }
}
