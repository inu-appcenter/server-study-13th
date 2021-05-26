package inu.appcenter.yunah.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import inu.appcenter.yunah.domain.Post;
import inu.appcenter.yunah.domain.status.PostStatus;
import inu.appcenter.yunah.model.post.PostCountDto;
import inu.appcenter.yunah.model.post.QPostCountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static inu.appcenter.yunah.domain.QComment.*;
import static inu.appcenter.yunah.domain.QPost.*;

@Repository
@RequiredArgsConstructor
public class PostQueryRepository {

    private final JPAQueryFactory queryFactory;

    // 삭제 상태가 아닌 게시글, 댓글
    public Post findPostById(Long postId) {
        Post result = queryFactory.select(post).distinct()
                .from(post)
                .leftJoin(post.commentList).fetchJoin()
                .where(post.id.eq(postId)
                        .and(post.status.eq(PostStatus.ACTIVE)))
                .fetchOne();
        return result;
    }

    // 삭제 상태가 아닌 게시글, 댓글 수
    public List<PostCountDto> findPostByCategory(Long categoryId) {

        List<PostCountDto> postCountDtoList = queryFactory.select(new QPostCountDto(post, comment.count())).distinct()
                .from(post)
                .leftJoin(post.commentList, comment)
                .where(post.status.eq(PostStatus.ACTIVE)
                        .and(post.category.id.eq(categoryId)))
                .groupBy(post)
                .fetch();
        return postCountDtoList;
    }
}
