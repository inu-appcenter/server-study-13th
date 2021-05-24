package inu.appcenter.chanhee.service;

import inu.appcenter.chanhee.domain.category.Category;
import inu.appcenter.chanhee.exception.CategoryException;
import inu.appcenter.chanhee.model.category.CategorySaveRequest;
import inu.appcenter.chanhee.repository.CategoryRepository;
import inu.appcenter.chanhee.repository.query.CategoryQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryQueryRepository categoryQueryRepository;

    // 카테고리 생성
    @Transactional
    public Long saveCategory(CategorySaveRequest categorySaveRequest) {

        Category category = Category.saveCategory(categorySaveRequest.getContent());

        Category saveCategory = categoryRepository.save(category);
        return saveCategory.getId();
    }

    // 카테고리 삭제
    @Transactional
    public void deleteCategory(Long categoryId) {

        // 카테고리 id가 존재하는지 확인
        Category findCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryException("해당 카테고리가 존재하지 않습니다."));

        // 카테고리가 존재한다면
        findCategory.deleteCategory(findCategory.getStatus());
    }

    // 카테고리 조회 (삭제 상태인 카테고리는 제외!)
    public List<Category> findAll() {

        List<Category> categories = categoryQueryRepository.findRegisterCategory();

        return categories;
    }
}
