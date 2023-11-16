package com.ssafy.priends.domain.alarm.service;

import com.ssafy.priends.domain.alarm.dto.AlarmInsertRequestDto;
import com.ssafy.priends.domain.alarm.dto.AlarmWithMemberDto;

import java.util.List;

public interface AlarmService {
    void createAlarm(AlarmInsertRequestDto alarmInsert, Long fromMemberId);

    List<AlarmWithMemberDto> getAlarmsWithMember(Long memberId);

    void deleteAlarm(Long alarmId);
}
