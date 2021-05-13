package com.example.hw2.controller;

import com.example.hw2.domain.ToDo;
import com.example.hw2.model.todo.request.ToDoSaveRequest;
import com.example.hw2.model.todo.response.ToDoResponse;
import com.example.hw2.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ToDoController {
    private final ToDoService toDoService;

    @PostMapping({"/members/{memberId}/todos"})
    public ResponseEntity saveToDo(@PathVariable Long memberId, @RequestBody ToDoSaveRequest toDoSaveRequest) {
        this.toDoService.save(memberId, toDoSaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping({"/todos/{todoId}"})
    public ResponseEntity patchTodo(@PathVariable Long todoId) {
        this.toDoService.patch(todoId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping({"/todos/{todoId}"})
    public ResponseEntity deleteByToDoId(@PathVariable Long todoId) {
        this.toDoService.deleteById(todoId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping({"/todos"})
    public List<ToDoResponse> findToDos() {
        List<ToDo> toDos = this.toDoService.findAll();
        return toDos.stream().map(ToDoResponse::new).collect(Collectors.toList());
    }

    @GetMapping({"/todos/{todoId}"})
    public ToDoResponse findToDoById(@PathVariable Long todoId) {
        ToDo toDo = this.toDoService.findById(todoId);
        ToDoResponse toDoResponse = new ToDoResponse(toDo);
        return toDoResponse;
    }
}