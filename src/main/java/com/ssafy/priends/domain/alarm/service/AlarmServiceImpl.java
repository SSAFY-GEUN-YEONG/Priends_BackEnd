package com.ssafy.priends.domain.alarm.service;

import com.ssafy.priends.domain.alarm.dto.AlarmInsertRequestDto;
import com.ssafy.priends.domain.alarm.dto.AlarmWithMemberDto;
import com.ssafy.priends.domain.alarm.mapper.AlarmMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService {

    private final AlarmMapper alarmMapper;

    @Override
    public void createAlarm(AlarmInsertRequestDto alarmInsert ,Long fromMemberId) {
        alarmMapper.createAlarm(alarmInsert, fromMemberId);
    }

    @Override
    public List<AlarmWithMemberDto> getAlarmsWithMember(Long memberId) {
        return alarmMapper.getAlarmsWithMember(memberId);
    }

    @Override
    public void deleteAlarm(Long alarmId) {
        alarmMapper.deleteAlarm(alarmId);
    }
}
