package inu.appcenter.yunah.service;

import inu.appcenter.yunah.domain.Member;
import inu.appcenter.yunah.domain.Todo;
import inu.appcenter.yunah.exception.MemberException;
import inu.appcenter.yunah.exception.TodoException;
import inu.appcenter.yunah.model.todo.TodoResponse;
import inu.appcenter.yunah.model.todo.TodoSaveRequest;
import inu.appcenter.yunah.model.todo.TodoUpdateRequest;
import inu.appcenter.yunah.repository.MemberRepository;
import inu.appcenter.yunah.repository.TodoRepository;
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

    @Transactional
    public void save(Long memberId, TodoSaveRequest todoSaveRequest) {

        // memberId로 회원 찾기
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));

        // todo 생성
        Todo todo = Todo.createTodo(todoSaveRequest.getContent(), todoSaveRequest.getIsCompleted(), findMember);
        todoRepository.save(todo);
    }

    public Todo findById(Long todoId) {

        // todoId로 todo 찾기(관련된 회원도 함께)
        Todo todo = todoRepository.findWithMemberById(todoId)
                .orElseThrow(() -> new TodoException("존재하지 않는 Todo입니다."));

        return todo;
    }

    @Transactional
    public void update(Long todoId, TodoUpdateRequest todoUpdateRequest) {

        // todoId로 todo 찾기
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new TodoException("존재하지 않는 Todo입니다."));

        // todo 수정
        todo.changeTodo(todoUpdateRequest.getIsCompleted());

    }

    @Transactional
    public void deleteById(Long todoId) {

        // todoId로 todo 찾기
        Todo findtodo = todoRepository.findById(todoId)
                .orElseThrow(() -> new TodoException("존재하지 않는 Todo입니다."));

        // todo 삭제
        todoRepository.delete(findtodo);

    }
}
