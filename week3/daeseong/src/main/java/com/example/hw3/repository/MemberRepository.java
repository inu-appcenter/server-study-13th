package com.example.hw3.repository;

import com.example.hw3.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    /**
     * 아래 두 개 모두, order가 없으면 member가 찾아지지 않는 문제가 있습니다.
     * 제가 코드를 잘못 짠건지 궁금합니다.
     */

    @Query("select distinct m from Member m join fetch m.orderList where m.id=:memberId")
    Optional<Member> findWithOrderListById(@Param("memberId") Long memberId);

    // Use List
    @Query("select distinct m from Member m join fetch m.orderList")
    List<Member> findWithOrderListAll();
}
