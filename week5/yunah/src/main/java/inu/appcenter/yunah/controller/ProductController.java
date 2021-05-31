package inu.appcenter.yunah.controller;

import inu.appcenter.yunah.domain.Product;
import inu.appcenter.yunah.model.product.ProductResponse;
import inu.appcenter.yunah.model.product.ProductSaveRequest;
import inu.appcenter.yunah.model.product.ProductUpdateRequest;
import inu.appcenter.yunah.service.ProductService;
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

    private final ProductService productService;

    /*
    상품 등록 ( ADMIN 권한을 가진 사용자만 허용 )
     */
    @PostMapping("/products")
    public ResponseEntity saveProduct(@RequestBody @Valid ProductSaveRequest productSaveRequest) {

        productService.saveProduct(productSaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /*
    상품 수정 ( ADMIN 권한을 가진 사용자만 허용 ) ( 수량, 가격만 )
     */
    @PatchMapping("/products/{productId}")
    public ResponseEntity updateProduct(@PathVariable Long productId, @RequestBody @Valid ProductUpdateRequest productUpdateRequest) {
        productService.updateProduct(productId, productUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /*
    상품 삭제 ( ADMIN 권한을 가진 사용자만 허용 )
     */
    @DeleteMapping("/products/{productId}")
    public ResponseEntity deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /*
    상품 리스트 조회
     */
    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> findAllProduct() {
        List<Product> productList = productService.findAllProduct();
        List<ProductResponse> products = productList.stream().map(product -> ProductResponse.from(product)).collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    /*
    상품 ID 조회
     */
    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductResponse> findProduct(@PathVariable Long productId) {
        Product product = productService.findProduct(productId);
        return ResponseEntity.ok(ProductResponse.from(product));
    }
}
