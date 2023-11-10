package com.ssafy.priends.global.component.jwt.service;

import com.ssafy.priends.global.component.jwt.dto.TokenDto;
import com.ssafy.priends.global.component.jwt.dto.TokenMemberInfoDto;
import lombok.NonNull;

public interface JwtService {
    // 토큰 발급
    TokenDto issueToken(@NonNull TokenMemberInfoDto tokenMemberInfoDto);

    // access 토큰 파싱
    TokenMemberInfoDto parseAccessToken(@NonNull String accessToken);

    // RefreshToken을 이용한 Access, Refresh 토큰 재발급
    TokenDto reissueToken(@NonNull String refreshToken);

    // 회원의 이메일을 이용한 redis에 해당 유저의 Refresh 토큰 삭제 (로그아웃 시 이용)
    void deleteRefreshToken(String memberEmail);

    // Refresh 토큰을 이용한 토큰 복호화 작업
    TokenMemberInfoDto decryptionRefreshToken(String refreshToken);
}
