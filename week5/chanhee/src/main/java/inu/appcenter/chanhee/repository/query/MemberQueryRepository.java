package inu.appcenter.chanhee.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import inu.appcenter.chanhee.domain.member.Member;
import inu.appcenter.chanhee.domain.member.MemberStatus;
import inu.appcenter.chanhee.domain.member.QMember;
import inu.appcenter.chanhee.domain.order.QOrder;
import inu.appcenter.chanhee.domain.product.QProduct;
import inu.appcenter.chanhee.model.member.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static inu.appcenter.chanhee.domain.member.QMember.*;
import static inu.appcenter.chanhee.domain.order.QOrder.*;
import static inu.appcenter.chanhee.domain.product.QProduct.*;

@RequiredArgsConstructor
@Repository
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Member> findAllWithOrderList() {

        List<Member> result = queryFactory
                .select(member).distinct()
                .from(member)
                .leftJoin(member.orderList, order)
                .leftJoin(order.product, product)
                .where(member.status.eq(MemberStatus.ACTIVE))
                .fetch();

        return result;
    }

    public Member findMemberWithOrderById(Long memberId) {

        Member result = queryFactory
                .select(member).distinct()
                .from(member)
                .leftJoin(member.orderList, order)
                .leftJoin(order.product, product)
                .where(member.id.eq(memberId)
                    .and(member.status.eq(MemberStatus.ACTIVE)))
                .fetchOne();

        return result;
    }
}
