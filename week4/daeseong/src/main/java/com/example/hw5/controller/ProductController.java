package com.example.hw5.controller;

import com.example.hw5.domain.Product;
import com.example.hw5.model.product.ProductPatchRequest;
import com.example.hw5.model.product.ProductResponse;
import com.example.hw5.model.product.ProductSaveRequest;
import com.example.hw5.service.ProductService;
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

    /**
     * ADMIN ONLY
     *
     * @param productSaveRequest
     * @return
     */
    @PostMapping("/products")
    public ResponseEntity<Void> saveProduct(@RequestBody @Valid ProductSaveRequest productSaveRequest) {

        productService.saveProduct(productSaveRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * ADMIN ONLY
     *
     * @param productPatchRequest
     * @param productId
     * @return
     */
    @PatchMapping("/products/{productId}")
    public ResponseEntity<Void> patchProduct(@RequestBody ProductPatchRequest productPatchRequest, @PathVariable Long productId) {

        productService.patchProduct(productPatchRequest, productId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * ADMIN ONLY
     *
     * @param productId
     * @return
     */
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {

        productService.deleteProduct(productId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/products")
    public List<ProductResponse> findAllProducts() {

        List<Product> products = productService.findAllProducts();

        return products.stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/products/{productId}")
    public ProductResponse findProduct(@PathVariable Long productId){
        Product product = productService.findProduct(productId);

        return new ProductResponse(product);
    }}
