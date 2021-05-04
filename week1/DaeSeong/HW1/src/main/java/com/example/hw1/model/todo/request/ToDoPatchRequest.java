package com.example.hw1.model.todo.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ToDoPatchRequest {
    private boolean isCompleted;
    private LocalDateTime updateAt;
}
