package inu.appcenter.study3_1.controller;

import inu.appcenter.study3_1.exception.AccessDeniedException;
import inu.appcenter.study3_1.exception.AuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

    @GetMapping("/exception/entrypoint")
    public ResponseEntity authenticationException(){
        throw new AuthenticationException("인증 오류가 발생하였습니다.");

    }

    @GetMapping("/exception/accessdenied")
    public ResponseEntity accessDeniedException(){
        throw new AccessDeniedException("권한이 없습니다.");
    }
}
