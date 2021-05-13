package inu.appcenter.jaekwon.controller;

import inu.appcenter.jaekwon.domain.Todo;
import inu.appcenter.jaekwon.model.todo.TodoResponseWithMember;
import inu.appcenter.jaekwon.model.todo.TodoSaveRequest;
import inu.appcenter.jaekwon.model.todo.TodoUpdateRequest;
import inu.appcenter.jaekwon.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    //  할일 생성(어느 회원꺼인지)
    @PostMapping("/members/{memberId}/todos")
    public ResponseEntity saveTodo(@PathVariable Long memberId,
                                   @RequestBody TodoSaveRequest todoSaveRequest){
        todoService.save(memberId, todoSaveRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //할일 완료여부 수정
    @PatchMapping("/todos/{todoId}")
    public ResponseEntity updateTodo(@PathVariable Long todoId,
                                     @RequestBody TodoUpdateRequest todoUpdateRequest){
        todoService.updateTodo(todoId, todoUpdateRequest);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //  할일 삭제
    @DeleteMapping("/todos/{todoId}")
    public ResponseEntity deleteTodo(@PathVariable Long todoId){
        todoService.delete(todoId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //  할일(1개) 조회 회원까지 같이
    @GetMapping("/todos/{todoId}")
    public TodoResponseWithMember findById(@PathVariable Long todoId){
        Todo todo = todoService.findById(todoId);

        return new TodoResponseWithMember(todo);
    }

}
