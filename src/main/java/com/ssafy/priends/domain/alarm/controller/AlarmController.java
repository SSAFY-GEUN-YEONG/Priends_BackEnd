package com.ssafy.priends.domain.alarm.controller;

import com.ssafy.priends.domain.alarm.dto.AlarmInsertRequestDto;
import com.ssafy.priends.domain.alarm.dto.AlarmWithMemberDto;
import com.ssafy.priends.domain.alarm.service.AlarmService;
import com.ssafy.priends.domain.member.dto.MemberLoginActiveDto;
import com.ssafy.priends.global.common.dto.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alarm")
@Slf4j
@RequiredArgsConstructor
public class AlarmController {
    private final AlarmService alarmService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<Message<Void>> createAlarm(@RequestBody AlarmInsertRequestDto alarmInsert,
                                                     @AuthenticationPrincipal MemberLoginActiveDto memberLoginActiveDto) {
        alarmService.createAlarm(alarmInsert, memberLoginActiveDto.getId());
        return ResponseEntity.ok().body(Message.success());
    }

    @GetMapping("/get/list")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<Message<List<AlarmWithMemberDto>>> getAlarmsWithMember(@AuthenticationPrincipal MemberLoginActiveDto memberLoginActiveDto) {
        List<AlarmWithMemberDto> alarmWithMemberList = alarmService.getAlarmsWithMember(memberLoginActiveDto.getId());
        return ResponseEntity.ok().body(Message.success(alarmWithMemberList));
    }

    @DeleteMapping("/delete/{alarmId}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<Message<Void>> deleteAlarm(@PathVariable("alarmId") Long alarmId) {
        alarmService.deleteAlarm(alarmId);
        return ResponseEntity.ok().body(Message.success());
    }
}
