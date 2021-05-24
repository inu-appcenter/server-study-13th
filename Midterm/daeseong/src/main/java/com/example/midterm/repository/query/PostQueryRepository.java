package com.example.midterm.repository.query;

import com.example.midterm.domain.Post;
import com.example.midterm.domain.QComment;
import com.example.midterm.domain.QPost;
import com.example.midterm.domain.status.CategoryStatus;
import com.example.midterm.domain.status.PostStatus;
import com.example.midterm.model.post.PostWithCommentCount;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostQueryRepository {

    private final JPAQueryFactory queryFactory;

    /**
     * Too... Hard...
     * Comment는 Delete시키면 DB에서 삭제시키는 것이므로 따로 상태 검사안하고 그냥 가져왔습니다.
     *
     * @param postId Post의 id값
     * @return Post에 comment 반환
     */
    public Post findPostWithComment(Long postId) {
        return queryFactory
                .select(QPost.post).distinct()
                .from(QPost.post)
                .leftJoin(QPost.post.commentList, QComment.comment)
                .where(QPost.post.id.eq(postId)
                        .and(QPost.post.status.eq(PostStatus.POSTED)))
                .fetchOne();
    }

    /**
     * 튜플을 List로 바꾸는게 너무 어렵습니다... ㅠㅠ
     * https://lelecoder.com/145 참고함 ㅠㅠ
     * @param categoryId 카테고리 아이디
     * @return PostWithCommentCount 반환
     */
    public List<PostWithCommentCount> findPostWithCommentCount(Long categoryId) {

        return queryFactory
                .select(Projections.constructor(PostWithCommentCount.class, QPost.post, QComment.comment.count())).distinct()
                .from(QPost.post)
                .leftJoin(QPost.post.commentList, QComment.comment)
                .groupBy(QPost.post)
                .where(QPost.post.category.id.eq(categoryId)
                        .and(QPost.post.status.eq(PostStatus.POSTED)))
                .fetch();
    }
}
