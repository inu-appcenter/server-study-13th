package com.example.midterm.model.category;

import com.example.midterm.domain.Category;
import lombok.Data;

@Data
public class CategoryResponse {

    private Long id;
    private String content;

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.content = category.getContent();
    }
}
