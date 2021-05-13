package inu.appcenter.yunah.repository;

import inu.appcenter.yunah.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query("select t from Todo t join fetch t.member where t.id = :todoId") // "select * from Todo t, member m where t.member_id = t.id and t.id = ?"
    Optional<Todo> findWithMemberById(@Param("todoId") Long todoId);

}
