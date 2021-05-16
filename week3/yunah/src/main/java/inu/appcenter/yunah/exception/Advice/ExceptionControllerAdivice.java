package inu.appcenter.yunah.exception.Advice;

import inu.appcenter.yunah.model.common.ExceptionResponse;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionControllerAdivice {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ExceptionResponse exceptionHandling(MethodArgumentNotValidException e) {

        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors()
                .forEach(error -> errors.put(((FieldError) error).getField(), error.getDefaultMessage()));
        return new ExceptionResponse(null, errors);
    }
}
