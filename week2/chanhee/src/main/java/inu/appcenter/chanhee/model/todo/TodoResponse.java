package inu.appcenter.chanhee.model.todo;

import inu.appcenter.chanhee.domain.Todo;
import inu.appcenter.chanhee.model.member.MemberResponse;
import lombok.Data;

@Data
public class TodoResponse {

    private Long id;

    private String content;

    private String isCompleted;

    private MemberResponse member;

    public TodoResponse(Todo todo) {
        this.id = todo.getId();
        this.content = todo.getContent();
        this.isCompleted = todo.getIsCompleted();
        this.member = new MemberResponse(todo.getMember());
    }
}
