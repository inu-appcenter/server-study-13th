package inu.appcenter.chanhee.repository;

import inu.appcenter.chanhee.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

// JpaRepository<class, ID>
public interface MemberRepository extends JpaRepository<Member, Long> {

    // "select m from Member m where m.email = email;
    //  -> "select * from Member m where m.email = email;
    Optional<Member> findByEmail(String email);

    @Query("select distinct m from Member m join fetch m.todoList where m.id=:memberId")
    Optional<Member> findWithTodoById(@Param("memberId") Long memberId);
}
