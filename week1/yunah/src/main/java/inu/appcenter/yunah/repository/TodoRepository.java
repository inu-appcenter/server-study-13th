package inu.appcenter.yunah.repository;

import inu.appcenter.yunah.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {  // <도메인 이름, 도메인 아이디의 타입>

}
