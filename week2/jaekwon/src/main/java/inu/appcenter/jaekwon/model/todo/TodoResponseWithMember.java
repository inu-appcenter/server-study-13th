package inu.appcenter.jaekwon.model.todo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import inu.appcenter.jaekwon.domain.Todo;
import inu.appcenter.jaekwon.model.member.MemberResponse;
import inu.appcenter.jaekwon.model.member.MemberResponseWithTodo;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoResponseWithMember {

    private Long todoId;
    private String content;
    private String isCompleted;
    private LocalDateTime updatedAt;

    private MemberResponse member;

    public TodoResponseWithMember(Todo todo){
        this.todoId = todo.getId();
        this.content = todo.getContent();
        this.isCompleted = todo.getIsCompleted();
        this.updatedAt = todo.getUpdatedAt();
        this.member = new MemberResponse(todo.getMember());
    }
}
