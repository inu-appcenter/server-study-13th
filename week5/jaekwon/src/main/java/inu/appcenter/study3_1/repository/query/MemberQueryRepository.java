package inu.appcenter.study3_1.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import inu.appcenter.study3_1.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static inu.appcenter.study3_1.domain.QMember.*;
import static inu.appcenter.study3_1.domain.QOrder.*;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Member> findWithOrderAndProductAllMember(){
        List<Member> findAllMember = queryFactory.
                select(member).distinct()
                .from(member)
                .leftJoin(member.orderList, order)
                .where(member.status.eq(MemberStatus.ACTIVE))
                .fetch();
        return findAllMember;
    }
}
