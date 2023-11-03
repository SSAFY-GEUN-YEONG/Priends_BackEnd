package com.ssafy.priends.global.component.alarm.service;

public interface AlarmService {
    void sendMailAlarm(String title, String contents, String receiver, String template);
}
