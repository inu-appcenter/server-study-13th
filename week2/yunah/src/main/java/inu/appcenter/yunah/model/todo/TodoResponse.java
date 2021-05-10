package inu.appcenter.yunah.model.todo;

import inu.appcenter.yunah.domain.Todo;
import inu.appcenter.yunah.model.member.MemberResponse;
import lombok.Data;

@Data
public class TodoResponse {

    private Long todoId;

    private String content;

    private String isCompleted;

    private MemberResponse member;

    public TodoResponse(Todo todo) {

        this.todoId = todo.getId();
        this.content = todo.getContent();
        this.isCompleted = todo.getIsCompleted();
        this.member = new MemberResponse(todo.getMember());
    }
}
