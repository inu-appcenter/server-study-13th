package inu.appcenter.chanhee.model.member;

import inu.appcenter.chanhee.domain.Member;
import inu.appcenter.chanhee.model.todo.TodoDto;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MemberByTodoResponse {
    private Long id;

    private String email;

    private int age;

    private String name;

    private List<TodoDto> todos;

    public MemberByTodoResponse(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.age = member.getAge();
        this.name = member.getName();
        this.todos = member.getTodoList().stream()
                .map(todo -> new TodoDto(todo))
                .collect(Collectors.toList());
    }
}
