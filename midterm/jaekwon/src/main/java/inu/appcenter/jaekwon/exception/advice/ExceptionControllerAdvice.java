package inu.appcenter.jaekwon.exception.advice;

import inu.appcenter.jaekwon.exception.CategoryException;
import inu.appcenter.jaekwon.exception.CommentException;
import inu.appcenter.jaekwon.exception.MemberException;
import inu.appcenter.jaekwon.exception.PostException;
import inu.appcenter.jaekwon.model.common.ExceptionResponse;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler({MethodArgumentNotValidException.class, MemberException.class, CategoryException.class, PostException.class, CommentException.class})
    public ExceptionResponse exceptionHanding(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors()
                .forEach(error -> errors.put(((FieldError) error).getField(), error.getDefaultMessage()));
        return new ExceptionResponse(null, errors);
    }
}
