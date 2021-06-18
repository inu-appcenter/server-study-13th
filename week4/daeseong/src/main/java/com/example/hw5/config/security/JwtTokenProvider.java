package com.example.hw5.config.security;

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

    private Long tokenValidMilliseconds = 1000L * 60 * 60;  // 1 Hour

    private final UserDetailsService userDetailsService;

    @PostConstruct  // 스프링 빈이 초기화되고 실행됌
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // 인증 객체 반환
    public Authentication getAuthentication(String jwtToken) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getMemberPk(jwtToken));

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // JWT 토큰 발급
    public String createToken(String memberPk) {
        Claims claims = Jwts.claims().setSubject(memberPk);  // PAYLOAD

        Date now = new Date();  // 시간

        return Jwts.builder()
                .setClaims(claims)  // PAYLOAD
                .setIssuedAt(now)  // 언제 발행
                .setExpiration(new Date(now.getTime() + tokenValidMilliseconds))  // 언제까지 유효
                .signWith(SignatureAlgorithm.HS256, secretKey)  // Signature
                .compact();
    }

    // 토큰을 발행할 때 넣었던 member의 Pk값이 나옴
    public String getMemberPk(String jwtToken) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken)
                .getBody().getSubject();
    }

    // JWT 토큰 검증
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);  // PAYLOAD

            return !claims.getBody().getExpiration().before(new Date());  // 시간이 유효한지 검증
        } catch (Exception e) {
            return false;
        }
    }
}
