package inu.appcenter.yunah.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import inu.appcenter.yunah.domain.Category;
import inu.appcenter.yunah.domain.status.CategoryStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static inu.appcenter.yunah.domain.QCategory.*;

@Repository
@RequiredArgsConstructor
public class CategoryQueryRepository {

    private final JPAQueryFactory queryFactory; // JpaConfig에 등록된 queryFactory

    // 삭제 상태가 아닌 카테고리 리스트
    public List<Category> findCategoryList() {

        List<Category> categoryList = queryFactory.select(category)
                .from(category)
                .where(category.status.eq(CategoryStatus.ACTIVE))
                .fetch();
        return categoryList;
    }
}
