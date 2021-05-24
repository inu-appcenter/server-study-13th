package inu.appcenter.chanhee.repository.query;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import inu.appcenter.chanhee.domain.Post.Post;
import inu.appcenter.chanhee.domain.Post.PostStatus;
import inu.appcenter.chanhee.model.post.PostWithCommentCountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static inu.appcenter.chanhee.domain.Post.QPost.*;
import static inu.appcenter.chanhee.domain.category.QCategory.*;
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

    // 카테고리 id에 속하는 해당 게시글 id 조회
    public List<Long> findPostId(Long categoryId) {

        List<Long> tuple = queryFactory
                .select(post.id)
                .from(post)
                .where(post.category.id.eq(categoryId)
                    .and(post.status.eq(PostStatus.POST)))
                .fetch();

        return tuple;
    }

    // 게시글과 댓글 수 조회
    public PostWithCommentCountDto findPostWithCommentCountsByPostId(Long postId) {

        Tuple tuple = queryFactory
                .select(post, comment.count())
                .from(post)
                .leftJoin(post.commentList, comment)
                .groupBy(post)
                .where(post.id.eq(postId))
                .fetchOne();

        PostWithCommentCountDto postWithCommentCountDto = new PostWithCommentCountDto();
        postWithCommentCountDto.setPost(tuple.get(post));
        postWithCommentCountDto.setCount(tuple.get(comment.count()));

        return postWithCommentCountDto;
    }
}
