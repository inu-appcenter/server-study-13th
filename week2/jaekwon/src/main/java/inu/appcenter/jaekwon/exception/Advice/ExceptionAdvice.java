package inu.appcenter.jaekwon.exception.Advice;

import inu.appcenter.jaekwon.exception.MemberException;
import inu.appcenter.jaekwon.exception.TodoException;
import inu.appcenter.jaekwon.model.comand.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler({MemberException.class, TodoException.class})
    public ResponseEntity exceptionHandler(Exception e){

        String message = e.getMessage();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(message));
    }
}
