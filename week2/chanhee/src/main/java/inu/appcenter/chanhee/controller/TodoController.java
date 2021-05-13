package inu.appcenter.chanhee.controller;

import inu.appcenter.chanhee.domain.Todo;
import inu.appcenter.chanhee.model.todo.TodoResponse;
import inu.appcenter.chanhee.model.todo.TodoSaveRequest;
import inu.appcenter.chanhee.model.todo.TodoUpdateRequest;
import inu.appcenter.chanhee.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j          // 로깅..?
public class TodoController {

    private final TodoService todoService;

    // todo 생성
    @PostMapping("/members/{memberId}/todos")
    public ResponseEntity saveTodo(@PathVariable Long memberId,
                                   @RequestBody TodoSaveRequest todoSaveRequest) {
        todoService.save(memberId, todoSaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // todo 수정
    @PatchMapping("todos/{todoId}")
    public ResponseEntity updateTodo(@PathVariable Long todoId,
                                     @RequestBody TodoUpdateRequest todoUpdateRequest) {
        todoService.update(todoId, todoUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // todo 삭제
    @DeleteMapping("todos/{todoId}")
    public ResponseEntity deleteTodo(@PathVariable Long todoId) {
        todoService.delete(todoId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // todo 식별자 조회 -> todo + 회원정보
    @GetMapping("/todos/{todoId}")
    public TodoResponse findById(@PathVariable Long todoId) {
        Todo todo = todoService.findById(todoId);

        return new TodoResponse(todo);
    }
}
