package inu.appcenter.yunah.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo extends BaseEntity {                    // 팀 - "회원" / 1 : "N"

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;

    private String content;

    private String isCompleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static Todo createTodo(String content, String isCompleted, Member member) {

        Todo todo = new Todo();
        todo.content = content;
        todo.isCompleted = isCompleted;
        todo.member = member;
        return todo;
    }

    public void changeTodo(String isCompleted) {

        this.isCompleted = isCompleted;
    }
}
