package com.ssafy.priends.global.component.jwt.service;

import com.ssafy.priends.global.component.jwt.JwtIssuer;
import com.ssafy.priends.global.component.jwt.JwtParser;
import com.ssafy.priends.global.component.jwt.JwtUtils;
import com.ssafy.priends.global.component.jwt.dto.TokenDto;
import com.ssafy.priends.global.component.jwt.dto.TokenMemberInfoDto;
import com.ssafy.priends.global.component.jwt.repository.RefreshRepository;
import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.ssafy.priends.global.component.jwt.JwtUtils.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class JwtServiceImpl implements JwtService {

    private final JwtUtils jwtUtils;
    private final JwtIssuer jwtIssuer;
    private final JwtParser jwtParser;
    private final RefreshRepository refreshRepository;

    // 토큰 발급
    @Override
    public TokenDto issueToken(@NonNull TokenMemberInfoDto tokenMemberInfoDto) {
        String accessToken = jwtIssuer.issueToken(
                tokenMemberInfoDto.toClaims(jwtUtils.getAccessTokenExpiredMin()), jwtUtils.getEncodedAccessKey()
        );

        String refreshToken = jwtIssuer.issueToken(
                tokenMemberInfoDto.toClaims(jwtUtils.getRefreshTokenExpiredMin()), jwtUtils.getEncodedRefreshKey()
        );

        // refreshToekn redis에 저장
        refreshRepository.save(tokenMemberInfoDto.getEmail(), refreshToken, jwtUtils.getRefreshTokenExpiredMin());

        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpiresIn(jwtUtils.getAccessTokenExpiredMin())
                .grantType(BEARER_PREFIX)
                .build();
    }

    // Access 토큰 파싱
    @Override
    public TokenMemberInfoDto parseAccessToken(@NonNull String accessToken) {
        Claims claims = jwtParser.parseToken(accessToken, jwtUtils.getEncodedAccessKey());
        if(claims == null) {
            return null;
        }
        return TokenMemberInfoDto.from(claims);
    }

    @Override
    public TokenDto reissueToken(@NonNull String refreshToken) {
        return null;
    }


    // redisTemplate을 통해 Refresh 토큰 삭제 (블랙리스트 추가) -> 로그아웃시 이용
    @Override
    public void deleteRefreshToken(String memberEmail) {
        refreshRepository.delete(memberEmail);
    }

    @Override
    public TokenMemberInfoDto decryptionRefreshToken(String refreshToken) {
        return null;
    }
}