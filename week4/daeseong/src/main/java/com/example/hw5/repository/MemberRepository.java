package com.example.hw5.repository;

import com.example.hw5.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    @Query("select distinct m from Member m left join fetch m.orderList")
    List<Member> findWithOrderListAll();

    @Query("select distinct m from Member m left join fetch m.roles where m.id=:memberId")
    Optional<Member> findWithRolesById(@Param("memberId") long parseLong);
}
