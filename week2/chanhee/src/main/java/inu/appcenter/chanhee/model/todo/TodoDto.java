package inu.appcenter.chanhee.model.todo;

import inu.appcenter.chanhee.domain.Todo;
import lombok.Data;

@Data
public class TodoDto {

    private Long id;

    private String content;

    private String isCompleted;

    public TodoDto(Todo todo) {
        this.id = todo.getId();
        this.content = todo.getContent();
        this.isCompleted = todo.getIsCompleted();
    }
}
