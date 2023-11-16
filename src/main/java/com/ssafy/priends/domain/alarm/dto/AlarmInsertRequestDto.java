package com.ssafy.priends.domain.alarm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AlarmInsertRequestDto {
    private String content; // 알람 내용
    private Long toMemberId;    // 알람 받을 회원의 id (쪽지 받을 회원의 id)
}
