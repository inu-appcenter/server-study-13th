package inu.appcenter.chanhee.controller;

import inu.appcenter.chanhee.domain.Todo;
import inu.appcenter.chanhee.model.todo.request.TodoSaveRequest;
import inu.appcenter.chanhee.model.todo.request.TodoUpdateRequest;
import inu.appcenter.chanhee.model.todo.response.TodoResponse;
import inu.appcenter.chanhee.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todos")
    public ResponseEntity saveTodo(@RequestBody TodoSaveRequest todoSaveRequest) {
        todoService.save(todoSaveRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/todos/{todoId}")
    public ResponseEntity updateTodo(@PathVariable Long todoId,
                                     @RequestBody TodoUpdateRequest todoUpdateRequest) {
        todoService.update(todoId, todoUpdateRequest);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/todos")
    public List<TodoResponse> findTodos() {
        List<Todo> todos = todoService.findAll();

        List<TodoResponse> todoResponses = todos.stream()
                .map(todo -> new TodoResponse(todo))
                .collect(Collectors.toList());

        return todoResponses;
    }

    @GetMapping("/todos/{todoId}")
    public TodoResponse findByTodoId(@PathVariable Long todoId) {
        Todo findTodo = todoService.findById(todoId);

        TodoResponse todoResponse = new TodoResponse(findTodo);
        return todoResponse;
    }

    @DeleteMapping("/todos/{todoId}")
    public ResponseEntity deleteByTodoId(@PathVariable Long todoId) {
        todoService.deleteById(todoId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
