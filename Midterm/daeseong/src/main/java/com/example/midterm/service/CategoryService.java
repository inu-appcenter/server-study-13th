package com.example.midterm.service;

import com.example.midterm.advice.exception.CategoryException;
import com.example.midterm.domain.Category;
import com.example.midterm.domain.status.CategoryStatus;
import com.example.midterm.model.category.CategorySaveRequest;
import com.example.midterm.repository.CategoryRepository;
import com.example.midterm.repository.query.CategoryQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public void saveCategory(CategorySaveRequest categorySaveRequest) {
        checkAlreadyExists(categorySaveRequest.getContent());

        Category savedCategory = Category.createCategory(categorySaveRequest.getContent());

        categoryRepository.save(savedCategory);
    }

    @Transactional
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryException("No Category Exists"));

        category.deleteCategory();
    }

    /**
     * 알아서 Activated 상태인 category만 찾아주네요. 굳.
     * @return Activated 상태인 category
     */
    public List<Category> findAllCategories() {
        List<Category> allCategory = categoryRepository.findAllByStatus(CategoryStatus.ACTIVATED);

        return allCategory;
    }

    /**
     * If content presents in categoryRepository,
     * throw CategoryException
     */
    private void checkAlreadyExists(String content) {
        if (categoryRepository.findByContent(content).isPresent()) {
            throw new CategoryException("Already Exists");
        }
    }
}
