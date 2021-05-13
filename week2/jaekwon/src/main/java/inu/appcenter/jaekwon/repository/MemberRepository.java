package inu.appcenter.jaekwon.repository;

import inu.appcenter.jaekwon.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    @Query("select distinct m from Member m join fetch m.todoList where m.id =:memberId")
    Optional<Member> findWithTodoById(@Param("memberId") Long memberId);
}
