package com.example.hw2.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ToDo extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;
    private String content;
    private boolean isCompleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static ToDo createToDo(String content, Member member) {
        ToDo todo = new ToDo();

        todo.content = content;
        todo.isCompleted = false;
        todo.member = member;

        return todo;
    }

    public void patchToDo() {
        this.isCompleted = true;
    }
}
