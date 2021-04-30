package inu.appcenter.jaekwon.model.todo.response;

import inu.appcenter.jaekwon.domain.Todo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TodoResponse {

    private Long id;
    private String content;
    private String isCompleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TodoResponse(Todo todo){
        this.id = todo.getId();
        this.content = todo.getContent();
        this.isCompleted = todo.getIsCompleted();
        this.createdAt = todo.getCreatedAt();
        this.updatedAt = todo.getUpdatedAt();
    }
}
