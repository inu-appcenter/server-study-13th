package inu.appcenter.yunah.model.member;

import inu.appcenter.yunah.domain.Member;
import inu.appcenter.yunah.model.todo.TodoDto;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MemberResponseTodo {

    private Long memberId;

    private String email;

    private int age;

    private String name;

    private List<TodoDto> todos;

    public MemberResponseTodo(Member member) {

        this.memberId = member.getId();
        this.email = member.getEmail();
        this.age = member.getAge();
        this.name = member.getName();
        this.todos = member.getTodoList().stream()
                .map(todo -> new TodoDto(todo))
                .collect(Collectors.toList());
    }
}
