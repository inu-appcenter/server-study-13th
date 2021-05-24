package inu.appcenter.yunah.model.category;

import inu.appcenter.yunah.domain.Category;
import inu.appcenter.yunah.domain.status.CategoryStatus;
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
