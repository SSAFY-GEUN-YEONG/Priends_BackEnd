package com.ssafy.priends.global.component.generate.service;

public interface GenerateService {
    void sendMailAlarm(String title, String contents, String receiver, String template);
}
