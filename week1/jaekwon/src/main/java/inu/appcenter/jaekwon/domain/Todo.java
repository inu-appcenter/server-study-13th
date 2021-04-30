package inu.appcenter.jaekwon.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;

    private String content;

    private String isCompleted;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static Todo createTodo(String content){
        Todo todo = new Todo();
        todo.content = content;
        todo.isCompleted = "unfinished";
        todo.createdAt = LocalDateTime.now();
        todo.updatedAt = null;
        return todo;
    }

    public void changeTodo(String isCompleted){
        this.isCompleted = "finished";
        this.updatedAt = LocalDateTime.now();
    }
}
