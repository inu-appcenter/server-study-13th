package inu.appcenter.jaekwon.controller;

import inu.appcenter.jaekwon.domain.Category;
import inu.appcenter.jaekwon.model.category.CategoryResponse;
import inu.appcenter.jaekwon.model.category.CategorySaveRequest;
import inu.appcenter.jaekwon.service.CategoryService;
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

    //  카테고리 생성
    @PostMapping("/categories")
    public ResponseEntity saveCategory(@RequestBody @Valid CategorySaveRequest categorySaveRequest){
        categoryService.saveCategory(categorySaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //  카테고리 삭제
    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable Long categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //  카테고리 조회
    @GetMapping("/categories")
    public List<CategoryResponse> findAllCategory(){
        List<Category> findAll = categoryService.findAll();
        List<CategoryResponse> result = findAll.stream().map(category -> new CategoryResponse(category))
                .collect(Collectors.toList());
        return result;
    }
}
