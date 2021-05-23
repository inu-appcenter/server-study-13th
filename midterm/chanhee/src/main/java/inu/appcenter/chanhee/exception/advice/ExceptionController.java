package inu.appcenter.chanhee.exception.advice;

import inu.appcenter.chanhee.exception.CategoryException;
import inu.appcenter.chanhee.exception.CommentException;
import inu.appcenter.chanhee.exception.MemberException;
import inu.appcenter.chanhee.exception.PostException;
import inu.appcenter.chanhee.model.common.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionResponse MethodArgumentNotValidExceptionHandling(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors()
                .forEach(error -> errors.put(((FieldError) error).getField(), error.getDefaultMessage()));

        return new ExceptionResponse("비어있는 정보란이 존재합니다.", errors);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ExceptionResponse HttpRequestMethodNotSupportedExceptionHandling(HttpRequestMethodNotSupportedException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put(e.getMethod(), e.getMessage());

        return new ExceptionResponse("경로가 잘못되었습니다.", errors);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ExceptionResponse MethodArgumentTypeMismatchExceptionHandling(MethodArgumentTypeMismatchException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put(e.getErrorCode(), e.getMessage());

        return new ExceptionResponse("주소에 숫자값을 입력해주세요.", errors);
    }

    @ExceptionHandler({MemberException.class, PostException.class, CategoryException.class, CommentException.class})
    public ResponseEntity ExceptionHandling(Exception e) {
        String message = e.getMessage();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(message,null));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity NullPointerExceptionHandling() {

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ExceptionResponse HttpMessageNotReadableExceptionHandling(Exception e) {
        Map<String, String> errors = new HashMap<>();
        errors.put(e.getMessage(), "");

        return new ExceptionResponse("JSON 형태를 확인해주세요.", errors);
    }

}
