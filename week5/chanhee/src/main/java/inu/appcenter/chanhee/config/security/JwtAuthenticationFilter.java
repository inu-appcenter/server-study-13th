package inu.appcenter.chanhee.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Security;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    private static final String AUTHORIZATION_HEADER = "Authorization";

    // 인증 작업
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        // HttpServletRequest의 헤더에서 Authorization이라는 헤더 값을 가져오는 메서드를 호출
        String jwtToken = resolveToken((HttpServletRequest) request);

        // token이 유효한지 검사
        if(StringUtils.hasText(jwtToken) && jwtTokenProvider.validateToken(jwtToken)) {
            // token이 유효하다면 인증 객체 생성
            Authentication authetication = jwtTokenProvider.getAuthetication(jwtToken);
            // 만들어온 인증 객체를 스레드로컬(저장소)의 SecurityContextHolder에 넣는다.
            SecurityContextHolder.getContext().setAuthentication(authetication);
        }
        chain.doFilter(request, response);
    }

    // HttpServletRequest의 헤더에서 Authorization이라는 헤더 값을 가져옴
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

        // 토큰이 존재하고 bearer로 시작한다면 bearer 제거하고 반환
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
