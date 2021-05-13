package com.example.hw2.service;

import com.example.hw2.domain.Member;
import com.example.hw2.domain.ToDo;
import com.example.hw2.model.todo.request.ToDoSaveRequest;
import com.example.hw2.repository.MemberRepository;
import com.example.hw2.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.module.FindException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ToDoService {
    private final ToDoRepository toDoRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void save(Long memberId, ToDoSaveRequest toDoSaveRequest) {
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(FindException::new);

        ToDo toDo = ToDo.createToDo(toDoSaveRequest.getContent(), findMember);
        this.toDoRepository.save(toDo);
    }

    @Transactional
    public void patch(Long toDoId) {
        ToDo findToDo = toDoRepository.findById(toDoId).orElseThrow(IllegalStateException::new);
        findToDo.patchToDo();
    }

    @Transactional
    public void deleteById(Long toDoId) {
        ToDo findToDo = toDoRepository.findById(toDoId).orElseThrow(IllegalStateException::new);
        this.toDoRepository.delete(findToDo);
    }

    public List<ToDo> findAll() {
        return this.toDoRepository.findAll();
    }

    public ToDo findById(Long toDoId) {
        return toDoRepository.findWithMemberId(toDoId)
                .orElseThrow(IllegalStateException::new);

    }
}

