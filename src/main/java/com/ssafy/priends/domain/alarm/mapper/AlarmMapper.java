package com.ssafy.priends.domain.alarm.mapper;

import com.ssafy.priends.domain.alarm.dto.AlarmInsertRequestDto;
import com.ssafy.priends.domain.alarm.dto.AlarmWithMemberDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AlarmMapper {
    void createAlarm(@Param("alarmInsert") AlarmInsertRequestDto alarmInsert,
                     @Param("fromMemberId") Long fromMemberId);

    List<AlarmWithMemberDto> getAlarmsWithMember(Long memberId);

    void deleteAlarm(Long alarmId);
}
