package inu.appcenter.yunah.config.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
인증 오류가 발생했을 때 핸들링 ( 인증 객체가 존재하지 않을 시 )
redirect -> /exception/entrypoint 호출
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {

        response.sendRedirect("/exception/entrypoint"); // url로 redirect 처리
    }
}
