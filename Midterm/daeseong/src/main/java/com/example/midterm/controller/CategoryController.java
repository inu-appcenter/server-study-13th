package com.example.midterm.controller;

import com.example.midterm.domain.Category;
import com.example.midterm.model.category.CategoryResponse;
import com.example.midterm.model.category.CategorySaveRequest;
import com.example.midterm.service.CategoryService;
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

    @PostMapping("/categories")
    public ResponseEntity<Void> saveCategory(@RequestBody @Valid CategorySaveRequest categorySaveRequest) {
        categoryService.saveCategory(categorySaveRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/categories")
    public List<CategoryResponse> findAllCategories() {
        List<Category> categoryList = categoryService.findAllCategories();

        return categoryList.stream().map(CategoryResponse::new).collect(Collectors.toList());
    }
}