package inu.appcenter.yunah.domain;

import inu.appcenter.yunah.domain.status.CategoryStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Category extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String content;

    @Enumerated(EnumType.STRING)
    private CategoryStatus status;

    public static Category saveCategory(String content) {

        Category category = new Category();
        category.content = content;
        category.status = CategoryStatus.ACTIVE;
        return category;
    }

    public void deleteCategory(Category category) {

        this.status = CategoryStatus.DELETED;
    }
}
