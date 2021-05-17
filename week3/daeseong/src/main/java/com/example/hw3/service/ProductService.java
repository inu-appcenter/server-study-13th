package com.example.hw3.service;

import com.example.hw3.domain.Member;
import com.example.hw3.domain.Product;
import com.example.hw3.exception.MemberException;
import com.example.hw3.model.member.MemberPatchRequest;
import com.example.hw3.model.member.MemberSaveRequest;
import com.example.hw3.model.product.ProductPatchRequest;
import com.example.hw3.model.product.ProductSaveRequest;
import com.example.hw3.repository.ProductRepository;
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
    public Long saveProduct(ProductSaveRequest productSaveRequest) {
        if(productRepository.findByName(productSaveRequest.getName()).isPresent())
        {
            throw new IllegalStateException();
        }

        Product product = Product.createProduct(productSaveRequest.getName(), productSaveRequest.getStackQuantity(), productSaveRequest.getPrice());

        Product savedProduct = productRepository.save(product);

        return savedProduct.getId();
    }

    @Transactional
    public void patchProduct(ProductPatchRequest productPatchRequest, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(RuntimeException::new);

        product.patch(productPatchRequest.getStackQuantity(), productPatchRequest.getPrice());
    }

    @Transactional
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(RuntimeException::new);

        product.delete();
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(()-> new MemberException("No Member"));
    }
}
