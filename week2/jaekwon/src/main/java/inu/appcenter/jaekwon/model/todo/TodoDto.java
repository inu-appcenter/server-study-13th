package inu.appcenter.jaekwon.model.todo;

import inu.appcenter.jaekwon.domain.Todo;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoDto {

    private Long id;
    private String content;
    private String isCompleted;
    private LocalDateTime updatedAt;

    public TodoDto(Todo todo){
        this.id = todo.getId();
        this.content = todo.getContent();
        this.isCompleted = todo.getIsCompleted();
        this.updatedAt = todo.getUpdatedAt();
    }
}
