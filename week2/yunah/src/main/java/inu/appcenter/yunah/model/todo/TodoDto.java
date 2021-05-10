package inu.appcenter.yunah.model.todo;

import inu.appcenter.yunah.domain.Todo;
import lombok.Data;

@Data
public class TodoDto {

    private Long todoId;

    private String content;

    private String isCompleted;

    public TodoDto(Todo todo) {

        this.todoId = todo.getId();
        this.content = todo.getContent();
        this.isCompleted = todo.getIsCompleted();
    }
}
