package inu.appcenter.yunah.controller;

import inu.appcenter.yunah.domain.Todo;
import inu.appcenter.yunah.model.todo.TodoResponse;
import inu.appcenter.yunah.model.todo.TodoSaveRequest;
import inu.appcenter.yunah.model.todo.TodoUpdateRequest;
import inu.appcenter.yunah.service.TodoService;
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

    /*
    Todo 생성
     */
    @PostMapping("/members/{memberId}/todos")
    public ResponseEntity saveTodo(@PathVariable Long memberId,
                                   @RequestBody TodoSaveRequest todoSaveRequest) {

        todoService.save(memberId, todoSaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /*
    Todo 수정(완료 여부)
     */
    @PatchMapping("/todos/{todoId}")
    public ResponseEntity updateTodo(@PathVariable Long todoId,
                                     @RequestBody TodoUpdateRequest todoUpdateRequest) {

        todoService.update(todoId, todoUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /*
    Todo 삭제
     */
    @DeleteMapping("/todos/{todoId}")
    public ResponseEntity deleteTodo(@PathVariable Long todoId) {

        todoService.deleteById(todoId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /*
    Todo 식별자 조회
     */
    @GetMapping("/todos/{todoId}")
    public TodoResponse findById(@PathVariable Long todoId) {

        Todo todo = todoService.findById(todoId);

        return new TodoResponse(todo);
    }
}
