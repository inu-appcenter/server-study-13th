package inu.appcenter.chanhee.model.todo.request;

import lombok.Data;

import java.time.LocalDateTime;

// 완료 여부만 변경

@Data
public class TodoUpdateRequest {

    private String isCompleted;

    private LocalDateTime updatedAt;

}
