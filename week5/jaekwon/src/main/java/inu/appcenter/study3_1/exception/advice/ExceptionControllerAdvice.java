package inu.appcenter.study3_1.exception.advice;

import com.querydsl.jpa.impl.JPAUtil;
import inu.appcenter.study3_1.exception.*;
import inu.appcenter.study3_1.model.common.ExceptionResponse;
import inu.appcenter.study3_1.model.common.ExceptionResponse2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionResponse exceptionHanding(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors()
                .forEach(error -> errors.put(((FieldError) error).getField(), error.getDefaultMessage()));
        return new ExceptionResponse(null, errors);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse2> handlingException(AccessDeniedException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ExceptionResponse2(e.getMessage()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionResponse2> handlingException1(AuthenticationException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionResponse2(e.getMessage()));
    }

    @ExceptionHandler({MemberException.class, OrderException.class, ProductException.class, IllegalStateException.class, UsernameNotFoundException.class})
    public ResponseEntity<ExceptionResponse2> handlingException2(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse2(e.getMessage()));
    }
}
