package inu.appcenter.yunah.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import inu.appcenter.yunah.domain.Member;
import inu.appcenter.yunah.domain.QMember;
import inu.appcenter.yunah.domain.QOrder;
import inu.appcenter.yunah.domain.QProduct;
import inu.appcenter.yunah.domain.status.MemberStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static inu.appcenter.yunah.domain.QMember.*;
import static inu.appcenter.yunah.domain.QOrder.*;
import static inu.appcenter.yunah.domain.QProduct.*;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;  // JpaConfig에 등록된 JPAQueryFactory

    public List<Member> findMemberWithOrderById() {
        List<Member> members = queryFactory.select(member).distinct()
                .from(member)
                .leftJoin(member.orderList, order).fetchJoin()
                .leftJoin(order.product, product).fetchJoin()
                .where(member.status.eq(MemberStatus.ACTIVE))
                .fetch();

        return members;
    }

    public Member findMemberById(Long memberId) {
        Member member = queryFactory.select(QMember.member).distinct()
                .from(QMember.member)
                .leftJoin(QMember.member.orderList, order).fetchJoin()
                .leftJoin(order.product, product).fetchJoin()
                .where(QMember.member.id.eq(memberId)
                        .and(QMember.member.status.eq(MemberStatus.ACTIVE)))
                .fetchOne();
        return member;
    }
}
