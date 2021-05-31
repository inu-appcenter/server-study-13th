package inu.appcenter.yunah.exception.advice;

import inu.appcenter.yunah.exception.*;
import inu.appcenter.yunah.model.common.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ExceptionResponse ValidexceptionHandling(MethodArgumentNotValidException e) {

        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors()
                .forEach(error -> errors.put(((FieldError) error).getField(), error.getDefaultMessage()));
        return new ExceptionResponse("잘못된 요청입니다.", errors);
    }

    @ExceptionHandler({MemberException.class, OrderException.class, ProductException.class})
    public ResponseEntity NotExistExceptionHandling(RuntimeException e) {
        String message = e.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    // 인가 오류
    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ExceptionResponse> handlingException(Exception e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ExceptionResponse(e.getMessage(), null));
    }

    // 인증 오류
    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<ExceptionResponse> handlingExceptionl(Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ExceptionResponse(e.getMessage(), null));
    }

}
