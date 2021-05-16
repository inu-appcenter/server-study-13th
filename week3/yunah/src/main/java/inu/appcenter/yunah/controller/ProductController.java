package inu.appcenter.yunah.controller;

import inu.appcenter.yunah.domain.Product;
import inu.appcenter.yunah.model.product.ProductResponse;
import inu.appcenter.yunah.model.product.ProductSaveRequest;
import inu.appcenter.yunah.model.product.ProductUpdateRequest;
import inu.appcenter.yunah.service.ProductSercvice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductSercvice productSercvice;

    /*
    상품 등록
     */
    @PostMapping("/products")
    public ResponseEntity saveProduct(@RequestBody @Valid ProductSaveRequest productSaveRequest) {

        productSercvice.saveProduct(productSaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /*
    상품 수정 ( 수량, 가격 변경 가능 )
     */
    @PatchMapping("/products/{productId}")
    public ResponseEntity updateProduct(@PathVariable Long productId, @RequestBody @Valid ProductUpdateRequest productUpdateRequest) {

        productSercvice.updateProduct(productId, productUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /*
    상품 삭제
     */
    @DeleteMapping("/products/{productId}")
    public ResponseEntity deleteProduct(@PathVariable Long productId) {

        productSercvice.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /*
    상품 리스트 조회
     */
    @GetMapping("/products")
    public List<ProductResponse> findAllProduct() {

        List<Product> products = productSercvice.findAll();
        List<ProductResponse> productResponseList = products.stream().map(product -> new ProductResponse(product)).collect(Collectors.toList());
        return productResponseList;
    }

    /*
    상품 Id 조회
     */
    @GetMapping("/products/{productId}")
    public ProductResponse findProduct(@PathVariable Long productId) {

        Product product = productSercvice.findById(productId);
        return new ProductResponse(product);
    }
}
