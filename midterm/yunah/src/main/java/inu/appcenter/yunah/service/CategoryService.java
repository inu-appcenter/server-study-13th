package inu.appcenter.yunah.service;

import inu.appcenter.yunah.domain.Category;
import inu.appcenter.yunah.exception.CategoryException;
import inu.appcenter.yunah.model.category.CategorySaveRequest;
import inu.appcenter.yunah.repository.CategoryRepository;
import inu.appcenter.yunah.repository.query.CategoryQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryQueryRepository categoryQueryRepository;

    @Transactional
    public void save(CategorySaveRequest categorySaveRequest) {

        Category category = Category.saveCategory(categorySaveRequest.getContent());
        categoryRepository.save(category);
    }

    @Transactional
    public void delete(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryException("존재하지 않는 카테고리입니다."));

        category.deleteCategory(category);
    }

    public List<Category> findAll() {
        List<Category> categoryList = categoryQueryRepository.findCategoryList();
        return categoryList;
    }
}
