package inu.appcenter.chanhee.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ExceptionController {

    @GetMapping("/exception/entrypoint")
    public ResponseEntity authenticationException() {
        throw new AuthenticationException("인증 오류가 발생하였습니다.");
    }

    @GetMapping("/exception/accessdenied")
    public ResponseEntity accessDeniedException() {
        throw new AccessDeniedException("권한이 없습니다.");
    }

}
