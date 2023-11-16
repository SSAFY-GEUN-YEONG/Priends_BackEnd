package com.ssafy.priends.domain.alarm.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AlarmWithMemberDto {
    private Long id;    // 알람 id
    private String content; // 알람 내용
    private Boolean read;   // 알람 수신 여부 (쪽지 읽었는지 여부)
    private Long fromMemberId;  // 알람 보낸 화원의 id (쪽지 보낸 회원의 id)
    private String fromMemberNickname;    // 알람 보낸 회원의 닉네임

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;    // 알람 보낸 날짜 (생성 날짜)
}
