package inu.appcenter.jaekwon.service;

import inu.appcenter.jaekwon.domain.Todo;
import inu.appcenter.jaekwon.model.todo.request.TodoSaveRequest;
import inu.appcenter.jaekwon.model.todo.request.TodoUpdateRequest;
import inu.appcenter.jaekwon.repository.TodoRepository;
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
    public void save(TodoSaveRequest todoSaveRequest) {
        Todo todo = Todo.createTodo(todoSaveRequest.getContent());

        todoRepository.save(todo);
    }

    @Transactional
    public void update(Long todoId, TodoUpdateRequest todoUpdateRequest) {
        Todo findTodo = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalStateException());

        findTodo.changeTodo(todoUpdateRequest.getIsCompleted());
    }

    @Transactional
    public void deleteById(Long todoId) {
        Todo findTodo = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalStateException());
        todoRepository.delete(findTodo);
    }

    public List<Todo> findAll() {
        List<Todo> findTodos = todoRepository.findAll();
        return findTodos;
    }

    public Todo findById(Long todoId) {
        Todo findMember = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalStateException());
        return findMember;
    }
}
