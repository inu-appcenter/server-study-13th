package inu.appcenter.chanhee.service;

import inu.appcenter.chanhee.domain.Todo;
import inu.appcenter.chanhee.model.todo.request.TodoSaveRequest;
import inu.appcenter.chanhee.model.todo.request.TodoUpdateRequest;
import inu.appcenter.chanhee.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {
    
    private final TodoRepository todoRepository;

    @Transactional
    public void save(TodoSaveRequest todoSaveRequest) {       // 저장
        Todo todo = Todo.createTodo(todoSaveRequest.getContent(), todoSaveRequest.getIsCompleted(),
                todoSaveRequest.getCreatedAt(), todoSaveRequest.getUpdatedAt());

        todoRepository.save(todo);
    }

    @Transactional
    public void update(Long todoId, TodoUpdateRequest todoUpdateRequest) {
        Todo findTodo = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalStateException());

        findTodo.changeTodo(todoUpdateRequest.getIsCompleted(), todoUpdateRequest.getUpdatedAt());
    }

    public List<Todo> findAll() {
        List<Todo> findTodos = todoRepository.findAll();

        return findTodos;
    }

    public Todo findById(Long todoId) {
        Todo findTodo = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalStateException());

        return findTodo;
    }

    @Transactional
    public void deleteById(Long todoId) {
        Todo findTodo = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalStateException());

        todoRepository.delete(findTodo);
    }
}

