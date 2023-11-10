package com.ssafy.priends.global.component.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Component;

import java.security.Key;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtParser {
    public Claims parseToken(String token, Key secretKey) {
        Claims claims;

        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            // 나중에 Exception 처리 해줘야함
            throw new RuntimeException("유효하지 않은 토큰입니다.");
        }

        return claims;
    }

}
