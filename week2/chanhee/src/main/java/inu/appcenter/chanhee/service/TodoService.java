package inu.appcenter.chanhee.service;

import inu.appcenter.chanhee.domain.Member;
import inu.appcenter.chanhee.domain.Todo;
import inu.appcenter.chanhee.exception.TodoException;
import inu.appcenter.chanhee.model.todo.TodoSaveRequest;
import inu.appcenter.chanhee.model.todo.TodoUpdateRequest;
import inu.appcenter.chanhee.repository.MemberRepository;
import inu.appcenter.chanhee.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {

    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    // Todo 생성
    @Transactional
    public void save(Long memberId, TodoSaveRequest todoSaveRequest) {
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new TodoException("존재하지 않는 회원 id 입니다."));

        Todo todo = Todo.createTodo(todoSaveRequest.getContent(), todoSaveRequest.getIsCompleted(), findMember);
        todoRepository.save(todo);
    }

    // Todo 수정 - 완료여부만 변경
    @Transactional
    public void update(Long todoId, TodoUpdateRequest todoUpdateRequest) {
        Todo findTodo = todoRepository.findById(todoId)
                .orElseThrow(() -> new TodoException("존재하지 않는 Todo id 입니다."));

        findTodo.changeTodo(todoUpdateRequest.getIsCompleted());
    }

    // Todo 삭제
    @Transactional
    public void delete(Long todoId) {
        Todo findTodo = todoRepository.findById(todoId)
                .orElseThrow(() -> new TodoException("존재하지 않는 Todo id 입니다."));

        todoRepository.delete(findTodo);
    }

    // Todo 조회
    public Todo findById(Long todoId) {
        Todo todo = todoRepository.findWithMemberById(todoId)
                .orElseThrow(() -> new TodoException("존재하지 않는 Todo id 입니다."));

        return todo;
    }
}
