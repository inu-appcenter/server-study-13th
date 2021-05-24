package inu.appcenter.jaekwon.service;

import inu.appcenter.jaekwon.domain.Category;
import inu.appcenter.jaekwon.domain.CategoryStatus;
import inu.appcenter.jaekwon.exception.CategoryException;
import inu.appcenter.jaekwon.model.category.CategorySaveRequest;
import inu.appcenter.jaekwon.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public void saveCategory(CategorySaveRequest categorySaveRequest) {
        if(categoryRepository.findByContent(categorySaveRequest.getContent()).isPresent()){
            throw new CategoryException("이미 생성된 카테고리 입니다.");
        }
        Category category = Category.createCategory(categorySaveRequest.getContent());
        categoryRepository.save(category);
    }

    @Transactional
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryException("없는 카테고리id 입니다."));
        category.deleteCategory();
    }

    public List<Category> findAll() {
        List<Category> findAllCategories = categoryRepository.findAllByStatus(CategoryStatus.ACTIVE);
        return findAllCategories;
    }
}
