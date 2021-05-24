package inu.appcenter.chanhee.controller;

import inu.appcenter.chanhee.domain.category.Category;
import inu.appcenter.chanhee.model.category.CategoryResponse;
import inu.appcenter.chanhee.model.category.CategorySaveRequest;
import inu.appcenter.chanhee.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // 카테고리 생성
    @PostMapping("/categories")
    public ResponseEntity saveCategory(@Valid @RequestBody CategorySaveRequest categorySaveRequest) {

        Long categoryId = categoryService.saveCategory(categorySaveRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryId);
    }

    // 카테고리 삭제
    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable Long categoryId) {

        categoryService.deleteCategory(categoryId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 카테고리 리스트 조회 (삭제 상태가 아닌 카테고리들만 조회!)
    @GetMapping("/categories")
    public List<CategoryResponse> findCategoryList() {
        List<Category> categories = categoryService.findAll();
        List<CategoryResponse> categoryResponseList = categories.stream()
                .map(category -> new CategoryResponse(category))
                .collect(Collectors.toList());

        return categoryResponseList;
    }
}
