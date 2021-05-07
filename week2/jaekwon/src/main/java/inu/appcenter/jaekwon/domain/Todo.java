package inu.appcenter.jaekwon.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;

    private String content;

    private String isCompleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static Todo createTodo(String content, Member member) {
        Todo todo = new Todo();
        todo.content = content;
        todo.isCompleted = "아직안함";
        todo.member = member;
        return todo;
    }

    public void changeIsCompleted(String isCompleted){
        this.isCompleted = "완료";
    }
}
