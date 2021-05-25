package inu.appcenter.yunah.controller;

import inu.appcenter.yunah.domain.Category;
import inu.appcenter.yunah.model.category.CategoryResponse;
import inu.appcenter.yunah.model.category.CategorySaveRequest;
import inu.appcenter.yunah.service.CategoryService;
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
public class CategoryController {

    private final CategoryService categoryService;

    /*
    카테고리 생성
     */
    @PostMapping("/categories")
    public ResponseEntity saveCategory(@RequestBody @Valid CategorySaveRequest categorySaveRequest) {

        categoryService.save(categorySaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /*
    카테고리 삭제 ( 상태 변경 )
     */
    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable Long categoryId) {

        categoryService.delete(categoryId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /*
    카테고리 리스트 조회 ( 삭제 상태가 아닌 카테고리들만 )
     */
    @GetMapping("/categories")
    public List<CategoryResponse> findAllCategories() {

        List<Category> categoryList = categoryService.findAll();
        List<CategoryResponse> categoryResponseList
                = categoryList.stream().map(category -> new CategoryResponse(category)).collect(Collectors.toList());
        return categoryResponseList;
    }
}
