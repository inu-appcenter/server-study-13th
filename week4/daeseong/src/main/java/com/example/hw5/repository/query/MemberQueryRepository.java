package com.example.hw5.repository.query;

import com.example.hw5.domain.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository  // 스프링 빈으로 등록, DB 오류나 JPA 오류를 스프링 오류로 전환 -> 에러 설명이 깔끔
@RequiredArgsConstructor
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;  // 우리가 등록한 JPAQueryFactory


    /**
     * ADMIN이 회원의 목록을 조회하는 것이므로, 회원의 상태는 신경쓰지 않음.
     *
     * @return List<Member>
     */
    public List<Member> findAllMembers() {

        return queryFactory
                .select(QMember.member).distinct()
                .from(QMember.member)
                .leftJoin(QMember.member.orderList, QOrder.order).fetchJoin()
                .leftJoin(QOrder.order.product, QProduct.product).fetchJoin()
                .fetch();
    }

    public Member findMemberById(Long memberId) {

        return queryFactory
                .select(QMember.member).distinct()
                .from(QMember.member)
                .leftJoin(QMember.member.orderList, QOrder.order).fetchJoin()
                .leftJoin(QOrder.order.product, QProduct.product).fetchJoin()
                .where(QMember.member.id.eq(memberId)
                        .and(QMember.member.status.eq(MemberStatus.ACTIVATED)))
                .fetchOne();
    }
}
