package com.example.hw2.model.todo.response;

import com.example.hw2.domain.ToDo;
import com.example.hw2.model.member.response.MemberResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ToDoResponse {
    private Long id;
    private String content;
    private boolean isCompleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private MemberResponse memberResponse;

    public ToDoResponse(ToDo toDo) {
        this.id = toDo.getId();
        this.content = toDo.getContent();
        this.isCompleted = toDo.isCompleted();
        this.createdAt = toDo.getCreatedAt();
        this.updatedAt = toDo.getUpdatedAt();
        this.memberResponse = new MemberResponse(toDo.getMember());
    }
}

