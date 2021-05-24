package inu.appcenter.yunah.repository.query;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import inu.appcenter.yunah.domain.Member;
import inu.appcenter.yunah.domain.QMember;
import inu.appcenter.yunah.domain.QPost;
import inu.appcenter.yunah.domain.status.MemberStatus;
import inu.appcenter.yunah.domain.status.PostStatus;
import inu.appcenter.yunah.exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static inu.appcenter.yunah.domain.QMember.*;
import static inu.appcenter.yunah.domain.QPost.*;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    // 삭제 상태가 아닌 회원과, 게시글
    public Member findMemberById(Long memberId) {

        Member result = queryFactory.select(member).distinct()
                .from(member)
                .leftJoin(member.postList, post).fetchJoin()
                .where(member.id.eq(memberId)
                        .and(member.status.eq(MemberStatus.ACTIVE))
                        .and(post.status.eq(PostStatus.ACTIVE)))
                .fetchOne();

        return result;
    }
}
