package inu.appcenter.chanhee.exception.Advice;

import inu.appcenter.chanhee.exception.MemberException;
import inu.appcenter.chanhee.exception.TodoException;
import inu.appcenter.chanhee.model.command.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

// 오류가 떴을 때 다른 오류로 내준다. 500 방지
@RestController         // 전역 컨트롤러
public class ExceptionAdvice {

    // 예외를 핸들링 -> 예외처리
    @ExceptionHandler({MemberException.class, TodoException.class})         // 예외 클래스
    public ResponseEntity exceptionHandler(Exception e) {
        String message = e.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(message));
    }
}
