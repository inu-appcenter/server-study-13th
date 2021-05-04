package inu.appcenter.jaekwon.model.todo.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoUpdateRequest {

    private String isCompleted;

}
