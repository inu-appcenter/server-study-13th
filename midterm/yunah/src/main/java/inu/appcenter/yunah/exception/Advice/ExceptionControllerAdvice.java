package inu.appcenter.yunah.exception.Advice;

import inu.appcenter.yunah.exception.CategoryException;
import inu.appcenter.yunah.exception.CommentException;
import inu.appcenter.yunah.exception.MemberException;
import inu.appcenter.yunah.exception.PostException;
import inu.appcenter.yunah.model.common.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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

    @ExceptionHandler({CategoryException.class, CommentException.class, MemberException.class, PostException.class})
    public ResponseEntity NotExistExceptionHandling(RuntimeException e) {
        String message = e.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity NotNullExceptionHandling(HttpMessageNotReadableException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 요청입니다.");
    }

    /*
    회원 Id 조회 시, 회원이 deleted인 경우
    게시글 Id 조회 시, 게시글이 deleted인 경우
    500(NullPointException) 오류 발생...
    */
    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity NullExceptionHandler(NullPointerException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제된 회원이거나 작성된 게시글이 존재하지 않습니다.");
    }
}

