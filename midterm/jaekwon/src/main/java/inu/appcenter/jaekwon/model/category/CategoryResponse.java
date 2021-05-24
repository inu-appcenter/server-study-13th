package inu.appcenter.jaekwon.model.category;

import inu.appcenter.jaekwon.domain.Category;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryResponse {

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public CategoryResponse(Category category){
        this.content = category.getContent();
        this.createdAt = category.getCreatedAt();
        this.updatedAt = category.getUpdatedAt();
    }
}
