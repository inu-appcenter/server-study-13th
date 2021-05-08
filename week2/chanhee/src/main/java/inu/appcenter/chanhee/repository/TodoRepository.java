package inu.appcenter.chanhee.repository;

import inu.appcenter.chanhee.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    Optional<Todo> findById(Long id);

    @Query("select t From Todo t join fetch t.member where t.id=:todoId")
    Optional<Todo> findWithMemberById(@Param("todoId") Long todoId);

}
