package com.example.hw2.model.todo.response;

import com.example.hw2.domain.ToDo;
import lombok.Data;

import java.io.Serializable;

@Data
public class ToDoDTO implements Serializable {
    private Long id;
    private String content;
    private boolean isCompleted;

    public ToDoDTO(ToDo toDo){
        this.id = toDo.getId();
        this.content = toDo.getContent();
        this.isCompleted = toDo.isCompleted();
    }
}
