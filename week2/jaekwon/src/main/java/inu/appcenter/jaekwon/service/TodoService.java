package inu.appcenter.jaekwon.service;

import inu.appcenter.jaekwon.domain.Member;
import inu.appcenter.jaekwon.domain.Todo;
import inu.appcenter.jaekwon.exception.MemberException;
import inu.appcenter.jaekwon.exception.TodoException;
import inu.appcenter.jaekwon.model.todo.TodoSaveRequest;
import inu.appcenter.jaekwon.model.todo.TodoUpdateRequest;
import inu.appcenter.jaekwon.repository.MemberRepository;
import inu.appcenter.jaekwon.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void save(Long memberId, TodoSaveRequest todoSaveRequest) {
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원id입니다."));

        Todo todo = Todo.createTodo(todoSaveRequest.getContent(), findMember);

        todoRepository.save(todo);
    }

    @Transactional
    public void updateTodo(Long todoId, TodoUpdateRequest todoUpdateRequest) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new TodoException("존재하지 않는 할일id입니다."));

        todo.changeIsCompleted(todoUpdateRequest.getIsCompleted());
    }

    @Transactional
    public void delete(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new TodoException("존재하지 않는 할일id입니다."));

        todoRepository.delete(todo);
    }

    public Todo findById(Long todoId) {
        Todo todo = todoRepository.findWithMemberById(todoId)
                .orElseThrow(() -> new TodoException("존재하지 않는 할일id입니다."));

        return todo;
    }
}
