package com.ssafy.priends.global.infra.email.service;

import com.ssafy.priends.global.common.dto.MailCodeDto;

public interface EmailService {
    MailCodeDto sendSimpleMessage(String to, boolean signUpCheck);
    void signUpCheck(String memberEmail, String requestSignUpCode);
}
