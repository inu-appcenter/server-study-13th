package inu.appcenter.chanhee.repository.query;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import inu.appcenter.chanhee.domain.Post.Post;
import inu.appcenter.chanhee.domain.Post.PostStatus;
import inu.appcenter.chanhee.model.post.PostWithCommentCount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static inu.appcenter.chanhee.domain.Post.QPost.*;
import static inu.appcenter.chanhee.domain.comment.QComment.*;

@Repository
@RequiredArgsConstructor
public class PostQueryRepository {

    private final JPAQueryFactory queryFactory;

    // 게시글과 댓글 조회
    public Post findWithCommentById(Long postId) {
        Post result = queryFactory
                .select(post).distinct()
                .from(post)
                .leftJoin(post.commentList, comment)
                .where(post.id.eq(postId)
                    .and(post.status.eq(PostStatus.POST)))
                .fetchOne();

        return result;
    }

    // 게시글과 댓글 수 조회
    public List<PostWithCommentCount> findPostwithCommentCountsByCategoryId(Long categoryId) {

        List<PostWithCommentCount> result = queryFactory
                .select(Projections.constructor(PostWithCommentCount.class, post, comment.count())).distinct()
                .from(post)
                .leftJoin(post.commentList, comment)
                .groupBy(post)
                .where(post.category.id.eq(categoryId)
                .and(post.status.eq(PostStatus.POST)))
                .fetch();

        return result;
    }

}
