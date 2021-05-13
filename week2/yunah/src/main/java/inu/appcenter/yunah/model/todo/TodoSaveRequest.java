package inu.appcenter.yunah.model.todo;

import lombok.Data;

@Data
public class TodoSaveRequest {

    private String content;

    private String isCompleted;

}
