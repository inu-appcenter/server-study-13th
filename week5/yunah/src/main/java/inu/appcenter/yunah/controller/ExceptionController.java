package inu.appcenter.yunah.controller;

import inu.appcenter.yunah.exception.AccessDeniedException;
import inu.appcenter.yunah.exception.AuthenticationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

    @GetMapping("/exception/entrypoint")
    public ResponseEntity authenticationException() {
        throw new AuthenticationException("인증 오류가 발생했습니다.");
    }

    @GetMapping("/exception/accessdenied")
    public ResponseEntity accessdeniedException() {
        throw new AccessDeniedException("권한이 없습니다.");
    }
}
