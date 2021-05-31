package inu.appcenter.chanhee.service;

import inu.appcenter.chanhee.domain.member.Member;
import inu.appcenter.chanhee.domain.product.Product;
import inu.appcenter.chanhee.exception.MemberException;
import inu.appcenter.chanhee.exception.ProductException;
import inu.appcenter.chanhee.model.product.ProductSaveRequest;
import inu.appcenter.chanhee.model.product.ProductUpdateRequest;
import inu.appcenter.chanhee.repository.MemberRepository;
import inu.appcenter.chanhee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    // 상품 등록
    @Transactional
    public void saveProduct(Long id, ProductSaveRequest productSaveRequest) {

        Member findMember = memberRepository.findById(id)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));

        // 회원 권한 확인

        Product product = Product.registerProduct(productSaveRequest.getName(),
                productSaveRequest.getStockQuantity(),
                productSaveRequest.getPrice());

        productRepository.save(product);
    }

    // 상품 수정
    @Transactional
    public void updateProduct(Long productId, ProductUpdateRequest productUpdateRequest) {

        // 회원 권한 확인하기 추가

        // 상픔 id가 존재하는지 확인
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException("존재하지 않는 상품입니다."));

        // 상품 id가 존재한다면
        product.updateProduct(productUpdateRequest.getPrice(), productUpdateRequest.getStockQuantity());
    }

    // 상품 삭제 (status만 변경)
    @Transactional
    public void deleteProduct(Long productId) {

        // 상품 id 존재여부 확인
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException("존재하지 않는 상품입니다."));

        // 상품이 존재한다면
        product.deleteProduct(product.getStatus());
    }

    // 상품 리스트 조회
    public List<Product> findAll() {

        List<Product> products = productRepository.findAll();
        return products;
    }

    // 상품 ID 조회
    public Product findProductById(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException("존재하지 않는 상품입니다."));

        return product;
    }
}
