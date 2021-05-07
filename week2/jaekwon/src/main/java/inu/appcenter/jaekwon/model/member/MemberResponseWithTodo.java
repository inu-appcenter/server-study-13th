package inu.appcenter.jaekwon.model.member;

import inu.appcenter.jaekwon.domain.Member;
import inu.appcenter.jaekwon.model.todo.TodoDto;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MemberResponseWithTodo {

    private Long memberId;
    private String email;
    private int age;
    private String name;
    private List<TodoDto> todos;

    public MemberResponseWithTodo(Member member){
        this.memberId = member.getId();
        this.email = member.getEmail();
        this.age = member.getAge();
        this.name = member.getName();
        this.todos = member.getTodoList().stream()
                .map(todo -> new TodoDto(todo))
                .collect(Collectors.toList());
    }
}
