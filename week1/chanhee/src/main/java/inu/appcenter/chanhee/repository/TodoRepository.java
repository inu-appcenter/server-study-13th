package inu.appcenter.chanhee.repository;

import inu.appcenter.chanhee.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
