package inu.appcenter.chanhee.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import inu.appcenter.chanhee.domain.Post.PostStatus;
import inu.appcenter.chanhee.domain.member.Member;
import inu.appcenter.chanhee.domain.member.MemberStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static inu.appcenter.chanhee.domain.Post.QPost.*;
import static inu.appcenter.chanhee.domain.member.QMember.member;

@RequiredArgsConstructor
@Repository
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Member> findMemberWithPostById(Long memberId) {
        List<Member> result = queryFactory
                .select(member).distinct()
                .from(member)
                .leftJoin(member.postList, post).fetchJoin()
                .where(member.id.eq(memberId)
                        .and(member.status.eq(MemberStatus.ACTIVE))
                        .and(post.status.eq(PostStatus.POST)
                        .or(member.postList.isEmpty())))
                .fetch();

        return result;
    }


}
