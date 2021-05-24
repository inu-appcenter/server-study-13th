package inu.appcenter.chanhee.repository.query;


import com.querydsl.jpa.impl.JPAQueryFactory;
import inu.appcenter.chanhee.domain.category.Category;
import inu.appcenter.chanhee.domain.category.CategoryStatus;
import inu.appcenter.chanhee.domain.category.QCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static inu.appcenter.chanhee.domain.category.QCategory.*;

@RequiredArgsConstructor
@Repository
public class CategoryQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Category> findRegisterCategory() {
        List<Category> result = queryFactory
                .select(category)
                .from(category)
                .where(category.status.eq(CategoryStatus.REGISTER))
                .fetch();

        return result;
    }

}
