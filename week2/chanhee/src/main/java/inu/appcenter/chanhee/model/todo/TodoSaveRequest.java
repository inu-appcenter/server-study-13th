package inu.appcenter.chanhee.model.todo;

import lombok.Data;

@Data
public class TodoSaveRequest {

    private Long id;

    private String content;

    private String isCompleted;
}
