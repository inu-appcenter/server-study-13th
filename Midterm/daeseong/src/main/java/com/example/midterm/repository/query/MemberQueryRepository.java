package com.example.midterm.repository.query;

import com.example.midterm.domain.Member;
import com.example.midterm.domain.QMember;
import com.example.midterm.domain.QPost;
import com.example.midterm.domain.status.MemberStatus;
import com.example.midterm.domain.status.PostStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Member findMemberWithPostsByMemberId(Long memberId) {

        return queryFactory
                .select(QMember.member).distinct()
                .from(QMember.member)
                .leftJoin(QMember.member.postList, QPost.post).fetchJoin()
                .where(QMember.member.id.eq(memberId)
                        .and(QMember.member.status.eq(MemberStatus.ACTIVATED))
                        .and(QPost.post.status.eq(PostStatus.POSTED)))
                .fetchOne();
    }
}
