package inu.appcenter.jaekwon.repository;

import inu.appcenter.jaekwon.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
