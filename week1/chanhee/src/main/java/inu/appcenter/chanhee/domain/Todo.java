package inu.appcenter.chanhee.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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

    @CreationTimestamp
    private LocalDateTime createdAt;

    @CreationTimestamp
    private LocalDateTime updatedAt;

    public static Todo createTodo(String content, String isCompleted,
                                  LocalDateTime createdAt, LocalDateTime updatedAt) {
        Todo todo = new Todo();
        todo.content = content;
        todo.isCompleted = isCompleted;
        todo.createdAt = createdAt;
        todo.updatedAt = updatedAt;

        return todo;
    }

    // 완료여부만 변경
    public void changeTodo(String isCompleted, LocalDateTime updatedAt) {
        this.isCompleted = isCompleted;
        this.updatedAt = LocalDateTime.now();
    }
}
