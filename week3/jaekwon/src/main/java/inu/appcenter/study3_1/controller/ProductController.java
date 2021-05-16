package inu.appcenter.study3_1.controller;

import inu.appcenter.study3_1.domain.Product;
import inu.appcenter.study3_1.model.product.ProductResponse;
import inu.appcenter.study3_1.model.product.ProductSaveRequest;
import inu.appcenter.study3_1.model.product.ProductUpdateRequest;
import inu.appcenter.study3_1.service.ProductService;
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

    //  상품 등록
    @PostMapping("/products")
    public ResponseEntity saveProduct(@RequestBody @Valid ProductSaveRequest productSaveRequest){
        productService.saveProduct(productSaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //  상품 수정
    @PatchMapping("/products/{productId}")
    public ResponseEntity updateProduct(@PathVariable Long productId,
                                        @RequestBody @Valid ProductUpdateRequest productUpdateRequest){
        productService.updateProduct(productId, productUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 상품 삭제
    @DeleteMapping("/products/{productId}")
    public ResponseEntity deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 상품 리스트 조회
    @GetMapping("/products")
    public List<ProductResponse> findAll(){
        List<Product> products = productService.findAll();
        List<ProductResponse> findProducts = products.stream().map(product -> new ProductResponse(product))
                .collect(Collectors.toList());
        return findProducts;
    }

    //  상품 ID 조회
    @GetMapping("/products/{productId}")
    public Product findById(@PathVariable Long productId){
        Product product = productService.findById(productId);
        return product;
    }
}
