package com.example.hw1.model.todo.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ToDoSaveRequest {
    private String content;
    private LocalDateTime createdAt;
}