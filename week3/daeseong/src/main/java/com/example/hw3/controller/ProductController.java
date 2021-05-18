package com.example.hw3.controller;

import com.example.hw3.domain.Product;
import com.example.hw3.model.product.ProductPatchRequest;
import com.example.hw3.model.product.ProductResponse;
import com.example.hw3.model.product.ProductSaveRequest;
import com.example.hw3.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ResponseEntity saveProduct(@RequestBody @Valid ProductSaveRequest productSaveRequest) {
        Long product = productService.saveProduct(productSaveRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PatchMapping("/products/{productId}")
    public ResponseEntity patchProduct(@RequestBody @Valid ProductPatchRequest productPatchRequest, @PathVariable Long productId) {
        productService.patchProduct(productPatchRequest, productId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/products")
    public List<ProductResponse> findAllProducts(){
        List<Product> products = productService.findAll();

        return products.stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/products/{productId}")
    public ProductResponse findProduct(@PathVariable Long productId){
        Product product = productService.findProduct(productId);

        return new ProductResponse(product);
    }
}
