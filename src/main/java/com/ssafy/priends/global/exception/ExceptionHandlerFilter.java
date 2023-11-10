package com.ssafy.priends.global.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.priends.global.common.dto.Message;
import com.ssafy.priends.global.component.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);    // 다음 필터 실행
        } catch (TokenException e) {
            // 에러 세팅 (response, 해당 Exception이 발생했을 때 에러 코드)
            setErrorResponse(response, e.getErrorCode(), request);
        }
    }

    private void setErrorResponse(HttpServletResponse response, GlobalError errorCode, HttpServletRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(errorCode.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8"); // utf-8로 설정해줘야 한글 안깨짐

        try {
            response.getWriter().write(objectMapper.writeValueAsString(Message.fail(errorCode.toString(), errorCode.getErrorMessage())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
