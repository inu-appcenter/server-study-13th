package inu.appcenter.chanhee.domain.category;

import inu.appcenter.chanhee.domain.BaseEntity;
import inu.appcenter.chanhee.domain.Post.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String content;

    @Enumerated(EnumType.STRING)
    private CategoryStatus status;

    // 카테고리 등록
    public static Category saveCategory(String content) {
        Category category = new Category();
        category.content = content;
        category.status = CategoryStatus.REGISTER;

        return category;
    }

    // 카테고리 삭제
    public void deleteCategory(CategoryStatus status) {
        this.status = CategoryStatus.DELETE;
    }
}
