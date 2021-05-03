package inu.appcenter.chanhee.model.todo.request;

// DTO : 단순하게 데이터를 전달해주는 역할

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoSaveRequest {

    private String content;

    private String isCompleted;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
