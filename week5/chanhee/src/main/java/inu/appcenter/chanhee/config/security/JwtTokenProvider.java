package inu.appcenter.chanhee.config.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

// 토큰의 생성, 토큰의 유효성 검증등을 담당
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${spring.jwt.secretKey}")
    private String secretKey;

    private Long tokenValidMilliseconds = 1000L * 60 * 60;   // 1시간 유효

    // UserDetailsService : DB에서 유저 정보를 가져오는 역할
    private final UserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // 회원의 id를 이용해 Token 발급
    public String createToken(String memberPk) {
        Claims claims = Jwts.claims().setSubject(memberPk);
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMilliseconds))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // JWT token 유효성 검사
    public boolean validateToken(String jwtToken) {
        try {
            /**
             *  secretKey를 통해 jwt를 파싱한다.
             *  만약 서버가 가지고 있는 secretKey로 파싱이 안된다면 변조되었거나 다른 서버의 jwt이므로 예외 발생
             */
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            // 유효시간 < 서버시간 --> false 리턴
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    // token에 저장되어 있는 회원의 정보를 가져온다.
    public Authentication getAuthetication(String jwtToken) {

        /**
         * userDetailsService.loadUserByUsername(회원 id)를 호출하여 UserDetails 타입을 객체에 반환
         * userDetailsService는 인터페이스 이므로 실제 호출은 MemberDeatilsService의 loadUserByUsername() 호출
         */
        UserDetails userDetails = userDetailsService.loadUserByUsername(getMemberPk(jwtToken));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 서버가 가지고 있는 secretKey를 활용해 jwt token 파싱, 토큰의 제목에서 회원의 id값 추출
    private String getMemberPk(String jwtToken) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken)
                .getBody().getSubject();
    }
}
