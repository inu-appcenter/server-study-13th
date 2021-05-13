package com.example.hw2.repository;

import com.example.hw2.domain.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    @Query("select t from ToDo t join fetch t.member where t.id =:toDoId")
    Optional<ToDo> findWithMemberId(@Param("toDoId") Long toDoId);
}