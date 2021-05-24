package com.example.midterm.repository;

import com.example.midterm.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    @Query("select m from Member m left join fetch m.postList where m.id =:memberId")
    Optional<Member> findMemberWithPostsByMemberId(Long memberId);
}
