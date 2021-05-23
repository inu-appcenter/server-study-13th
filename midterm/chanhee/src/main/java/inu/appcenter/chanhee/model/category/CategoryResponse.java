package inu.appcenter.chanhee.model.category;

import inu.appcenter.chanhee.domain.category.Category;
import inu.appcenter.chanhee.domain.category.CategoryStatus;
import lombok.Data;

@Data
public class CategoryResponse {

    private Long categoryId;

    private String content;

    private CategoryStatus status;

    public CategoryResponse(Category category) {
        this.categoryId = category.getId();
        this.content = category.getContent();
        this.status = category.getStatus();
    }
}
