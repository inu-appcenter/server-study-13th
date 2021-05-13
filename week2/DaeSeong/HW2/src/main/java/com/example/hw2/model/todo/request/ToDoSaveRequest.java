package com.example.hw2.model.todo.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ToDoSaveRequest {
    private String content;
    private LocalDateTime createdAt;
}
