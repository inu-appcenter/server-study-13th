package inu.appcenter.yunah.exception.Advice;

import inu.appcenter.yunah.exception.MemberException;
import inu.appcenter.yunah.exception.TodoException;
import inu.appcenter.yunah.model.command.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
잘못된 요청임을 명시해줌
 */

@RestControllerAdvice // 전역 컨트롤러
public class ExceptionAdvice {

    // 예외 처리
    @ExceptionHandler({MemberException.class, TodoException.class})
    public ResponseEntity exceptionHandler(Exception e) {  // 가장 최상, 부모(Exception)

        String message = e.getMessage();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(message)); // JSON
    }
}
