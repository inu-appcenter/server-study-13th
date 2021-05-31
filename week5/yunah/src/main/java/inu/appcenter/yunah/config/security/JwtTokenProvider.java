package inu.appcenter.yunah.config.security;

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

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${spring.jwt.secretKey}")
    private String secretKey;

    private Long tokenValidMilliseconds = 1000L * 60 * 60;  // 토큰의 유효한 시간(1시간)

    // MemberDetailsService(인터페이스인 UserDetailsService를 구현해준 것)이 들어가게 된다.
    private final UserDetailsService userDetailsService;    // 의존성 주입. 회원의 정보(인증된 객체)를 데이터베이스로부터 가져온다.

    @PostConstruct  // 스프링 빈이 초기화되고 나서 실행되도록 한다.
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());   // 인코딩을 해주어서 암호가 바로 들어나지 않도록 암호화하여 보안을 높인다.
    }

    public String createToken(String memberPk) {

        Claims claims = Jwts.claims().setSubject(memberPk); // Payload에 회원의 Id를 넣어준다.
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)  // Payload 설정
                .setIssuedAt(now)   // 토큰 발행 시간
                .setExpiration(new Date(now.getTime() + tokenValidMilliseconds))  // 토큰의 만료되는 시간
                .signWith(SignatureAlgorithm.HS256, secretKey)   // 알고리즘을 이용하여 토큰을 sign한다.( Signature )
                .compact();
    }

    public boolean validateToken(String jwtToken) {

        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);  // payload
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public Authentication getAuthentication(String jwtToken) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getMemberPk(jwtToken));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getMemberPk(String jwtToken) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken)
                .getBody().getSubject();
    }

}