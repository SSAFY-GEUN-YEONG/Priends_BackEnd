package com.ssafy.priends.global.component.jwt.dto;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

import static javax.management.timer.Timer.ONE_MINUTE;
import static com.ssafy.priends.global.component.jwt.JwtUtils.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class TokenMemberInfoDto {
    private Long id;
    private String email;
    private String nickname;
    private String image;
    private boolean alarm;
    private String role;

    public Claims toClaims(int expiresMin) {
        Claims claims = Jwts.claims();

        Date now = new Date();

        claims.put(KEY_ID, this.id);
        claims.put(KEY_EMAIL, this.email);
        claims.put(KEY_NICKNAME, this.nickname);
        claims.put(KEY_IMAGE, this.image);
        claims.put(KEY_ALARM, this.alarm);
        claims.put(KEY_ROLE, this.role);
        claims.setExpiration(new Date(now.getTime() + expiresMin * ONE_MINUTE));

        // expire 시간은 TokenProvider에서 설정
        return claims;
    }

    public static TokenMemberInfoDto from(Claims claims) {
        return TokenMemberInfoDto.builder()
                .id(claims.get(KEY_ID, Long.class))
                .email(claims.get(KEY_EMAIL, String.class))
                .nickname(claims.get(KEY_NICKNAME, String.class))
                .image(claims.get(KEY_IMAGE, String.class))
                .alarm(claims.get(KEY_ALARM, Boolean.class))
                .role(claims.get(KEY_ROLE, String.class))
                .build();
    }

    // 로그인 활성화 유저 변환


}
