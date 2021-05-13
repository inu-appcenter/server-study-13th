package com.example.hw2.model.todo.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ToDoPatchRequest {
    private boolean isCompleted;
    private LocalDateTime updateAt;
}