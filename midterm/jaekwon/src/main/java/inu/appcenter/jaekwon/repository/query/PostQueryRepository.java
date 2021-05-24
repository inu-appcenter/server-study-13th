package inu.appcenter.jaekwon.repository.query;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import inu.appcenter.jaekwon.domain.*;
import inu.appcenter.jaekwon.model.post.PostDto;
import inu.appcenter.jaekwon.model.post.PostWithCommentCount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PostQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<PostWithCommentCount> findPostWithCommentCountsByCategoryId(Long categoryId){
        List<PostWithCommentCount> result = queryFactory.select(
                Projections.constructor(PostWithCommentCount.class, QPost.post, QComment.comment.count())).distinct()
                .from(QPost.post)
                .leftJoin(QPost.post.commentList, QComment.comment)
                .groupBy(QPost.post)
                .where(QPost.post.category.id.eq(categoryId).and(QPost.post.status.eq(PostStatus.ACTIVE)))
                .fetch();

        return result;
    }
}
