package com.example.midterm.domain;

import com.example.midterm.domain.status.CategoryStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String content;

    @Enumerated(EnumType.STRING)
    private CategoryStatus status;

    public static Category createCategory(String content) {
        Category category = new Category();

        category.content = content;
        category.status = CategoryStatus.ACTIVATED;

        return category;
    }

    public void deleteCategory() {
        this.status = CategoryStatus.DEACTIVATED;
    }
}
