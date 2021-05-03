package com.example.hw1.model.todo.response;

import com.example.hw1.domain.ToDo;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ToDoResponse {
    private Long id;
    private String content;
    private boolean isCompleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ToDoResponse(ToDo toDo) {
        this.id = toDo.getId();
        this.content = toDo.getContent();
        this.isCompleted = toDo.isCompleted();
        this.createdAt = toDo.getCreatedAt();
        this.updatedAt = toDo.getUpdatedAt();
    }
}
