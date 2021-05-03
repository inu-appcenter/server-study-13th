package com.example.hw1.service;

import com.example.hw1.domain.ToDo;
import com.example.hw1.model.todo.request.ToDoSaveRequest;
import com.example.hw1.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ToDoService {
    private final ToDoRepository toDoRepository;

    @Transactional
    public void save(ToDoSaveRequest toDoSaveRequest) {
        ToDo toDo = ToDo.createToDo(toDoSaveRequest.getContent());
        this.toDoRepository.save(toDo);
    }

    @Transactional
    public void patch(Long toDoId) {
        ToDo findToDo = (ToDo)this.toDoRepository.findById(toDoId).orElseThrow(IllegalStateException::new);
        findToDo.patchToDo();
    }

    @Transactional
    public void deleteById(Long toDoId) {
        ToDo findToDo = (ToDo)this.toDoRepository.findById(toDoId).orElseThrow(IllegalStateException::new);
        this.toDoRepository.delete(findToDo);
    }

    public List<ToDo> findAll() {
        return this.toDoRepository.findAll();
    }

    public ToDo findById(Long toDoId) {
        return (ToDo)this.toDoRepository.findById(toDoId).orElseThrow(IllegalStateException::new);
    }
}
