package inu.appcenter.chanhee.exception.advice;

import inu.appcenter.chanhee.exception.*;
import inu.appcenter.chanhee.model.common.ExceptionResponse;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
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

// ExceptionController에서 발생시킨 오류를 핸들링
@RestControllerAdvice
public class ExceptionHandlingAdvice {

     // 인가 오류
     @ExceptionHandler({AccessDeniedException.class})
     public ResponseEntity<ExceptionResponse> handlingException(AccessDeniedException e) {
         return ResponseEntity
                 .status(HttpStatus.FORBIDDEN)
                 .body(new ExceptionResponse(e.getMessage(), null));
     }

     // 인증 오류
     @ExceptionHandler({AuthenticationException.class})
     public ResponseEntity<ExceptionResponse> handlingException1(AuthenticationException e) {
         return ResponseEntity
                 .status(HttpStatus.UNAUTHORIZED)
                 .body(new ExceptionResponse(e.getMessage(), null));
     }

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

    @ExceptionHandler({MemberException.class, ProductException.class, OrderException.class})
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

    @ExceptionHandler(IncorrectResultSizeDataAccessException.class)
    public ExceptionResponse IncorrectResultSizeDataAccessException(Exception e) {
        Map<String, String> errors = new HashMap<>();
        errors.put(e.getMessage(), "");

        return new ExceptionResponse("데이터베이스에 중복된 값이 있습니다.", errors);
    }
}
