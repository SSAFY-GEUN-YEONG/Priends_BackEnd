package com.ssafy.priends.global.component.jwt.security;

import com.ssafy.priends.domain.member.dto.MemberLoginActiveDto;
import com.ssafy.priends.global.component.jwt.dto.TokenMemberInfoDto;
import com.ssafy.priends.global.component.jwt.service.JwtService;
import com.ssafy.priends.global.exception.GlobalError;
import com.ssafy.priends.global.exception.TokenException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    private final JwtService jwtService;

    // JWT 토큰의 인증 정보를 현재 쓰레드의 SecirityContext에 저장하는 역할 수행하는 필터
    // 실제 필터링 로직은 doFilterInternal에 들어감
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Request Header에서 JWT 토큰을 추출
        String jwt = getJwtToken(request);
        // 추출된 JWT로 사용자 인증 시도
        authenticate(request, jwt);
        // 필터 체인을 계속 진행
        filterChain.doFilter(request, response);
    }

    // Request Header에서 토큰 정보를 꺼내오기
    public String getJwtToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public void authenticate(HttpServletRequest request, String token) {
        TokenMemberInfoDto tokenMemberInfoDto = null;
        if(StringUtils.hasText(token)) {
            tokenMemberInfoDto = jwtService.parseAccessToken(token);
            try {
                MemberLoginActiveDto loginActiveDto = MemberLoginActiveDto.from(tokenMemberInfoDto);
                saveLoginMemberInSecurityContext(loginActiveDto);
            } catch(RuntimeException e) {
                SecurityContextHolder.clearContext();
                throw new TokenException(GlobalError.INVALID_TOKEN);
            }
        }
    }

    private static void saveLoginMemberInSecurityContext(MemberLoginActiveDto memberLoginActiveDto) {
        JwtAuthenticationToken authentication = new JwtAuthenticationToken(
                memberLoginActiveDto, "", Arrays.asList(new SimpleGrantedAuthority(memberLoginActiveDto.getRole()))
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
