package com.ssafy.priends.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum GlobalError {
    NOT_AUTHORITY_MEMBER_API(HttpStatus.FORBIDDEN, "해당 API 호출에 대한 권한이 없습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 인증 토큰입니다."),
    ACCESS_EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "RefreshToken은 살아있지만 AccessToken이 만료되었습니다"),
    CERTIFICATION_NOT_TOKEN(HttpStatus.UNAUTHORIZED, "자격 증명이 되어 있지 않은 토큰입니다."),
    REDIS_NOT_TOKEN(HttpStatus.INTERNAL_SERVER_ERROR, "해당 이메일에 저장된 RefreshToken이 Redis에 저장되어 있지 않아 재발급이 불가능합니다.");

    private final HttpStatus httpStatus;
    private final String errorMessage;
}
