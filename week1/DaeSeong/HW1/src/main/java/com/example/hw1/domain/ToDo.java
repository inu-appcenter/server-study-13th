package com.example.hw1.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;
    private String content;
    private boolean isCompleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ToDo createToDo(String content) {
        ToDo todo = new ToDo();
        todo.content = content;
        todo.isCompleted = false;
        todo.createdAt = LocalDateTime.now();
        todo.updatedAt = LocalDateTime.now();
        return todo;
    }

    public void patchToDo() {
        this.isCompleted = true;
        this.updatedAt = LocalDateTime.now();
    }
}
