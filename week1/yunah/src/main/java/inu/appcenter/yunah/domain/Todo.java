package inu.appcenter.yunah.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)       //생성자 생성..
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Idendtity 전략(id 값 자동 설정)
    @Column(name = "todo_id")

    private Long id;                 // 식별자

    private String content;          // 내용

    private boolean isCompleted;     // 완료여부

    private LocalDateTime createdAt;  // 생성날짜

    private LocalDateTime updatedAt;  // 완료날짜

    public static Todo createTodo(String content) {
        Todo todo = new Todo();
        todo.content = content;                  // 내용
        todo.isCompleted = false;                // 완료 X
        todo.createdAt = LocalDateTime.now();    // 컴퓨터의 현재 날짜와 시간
        todo.updatedAt = null;                   // 완료 X
        return todo;
    }

    public void changeTodo(boolean completed) {
        this.isCompleted = completed;
        this.updatedAt = LocalDateTime.now();
    }
}
