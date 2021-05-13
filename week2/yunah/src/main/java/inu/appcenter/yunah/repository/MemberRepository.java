package inu.appcenter.yunah.repository;

import inu.appcenter.yunah.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // "select m from Member where m.email" -> select * from Todo t where t.email = email"
    Optional<Member> findByEmail(String email);

    @Query("select distinct m from Member m join fetch m.todoList where m.id = :memberId")
    Optional<Member> findWithTodoById(@Param("memberId") Long memberId);
}
