package inu.appcenter.chanhee.model.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import inu.appcenter.chanhee.domain.Member;
import inu.appcenter.chanhee.model.todo.TodoDto;
import lombok.Data;

import java.util.List;

@Data
public class MemberResponse {

    private Long id;

    private String email;

    private int age;

    private String name;

    @JsonIgnore
    private List<TodoDto> todos;

    public MemberResponse(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.age = member.getAge();
        this.name = member.getName();
    }
}
