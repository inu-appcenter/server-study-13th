package inu.appcenter.chanhee.controller;

import inu.appcenter.chanhee.domain.product.Product;
import inu.appcenter.chanhee.model.product.ProductResponse;
import inu.appcenter.chanhee.model.product.ProductSaveRequest;
import inu.appcenter.chanhee.model.product.ProductUpdateRequest;
import inu.appcenter.chanhee.service.ProductService;
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

    // 상품 등록
    @PostMapping("/products")
    public ResponseEntity saveProduct(@Valid @RequestBody ProductSaveRequest productSaveRequest) {

        Long productId = productService.saveProduct(productSaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }

    // 상품 수정
    @PatchMapping("/products/{productId}")
    public ResponseEntity updateProduct(@PathVariable Long productId,
                                        @RequestBody ProductUpdateRequest productUpdateRequest) {

        productService.updateProduct(productId, productUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 상품 삭제
    @DeleteMapping("/products/{productId}")
    public ResponseEntity deleteProduct(@PathVariable Long productId) {

        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 상품 리스트 조회
    @GetMapping("/products")
    public List<ProductResponse> findAllProduct() {
        List<Product> products = productService.findAll();
        List<ProductResponse> productResponsesList = products.stream()
                .map(product -> new ProductResponse(product))
                .collect(Collectors.toList());

        return productResponsesList;
    }

    // 상품 ID 조회
    @GetMapping("/products/{productId}")
    public ProductResponse findProductById(@PathVariable Long productId) {

        Product product = productService.findProductById(productId);

        return new ProductResponse(product);
    }


}
