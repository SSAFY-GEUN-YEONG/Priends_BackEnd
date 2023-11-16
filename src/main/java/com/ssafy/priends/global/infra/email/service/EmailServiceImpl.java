package com.ssafy.priends.global.infra.email.service;

import com.ssafy.priends.global.common.dto.MailCodeDto;
import com.ssafy.priends.global.component.generate.service.GenerateService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final GenerateService alarmService;

    private static final int EXPIRES_MIN = 30;  // 인증코드 인증 제한시간 30분

    @Override
    public MailCodeDto sendSimpleMessage(String to, boolean signUpCheck) {
        String ePw = createKey();

        try {
            if(signUpCheck) {
                alarmService.sendMailAlarm("이메일 인증코드 입니다.", createMessage(to, signUpCheck, ePw), to,
                        "alarm");
                // 여기에 이메일 redis에 추가하기

            } else {
                alarmService.sendMailAlarm("임시 비밀번호 입니다.", createMessage(to, signUpCheck, ePw), to,
                        "alarm");
            }
        } catch (MailException e) {
            e.printStackTrace();
            throw new RuntimeException("이메일 보낼 수 없습니다.");
        }
        return MailCodeDto.builder().userEmail(to).emailCode(ePw).build();
    }

    @Override
    public void signUpCheck(String memberEmail, String requestSignUpCode) {

    }

    private String createMessage(String to, boolean signUpCheck, String key) {
        StringBuilder sb = new StringBuilder();

        sb.append("아래 코드를 복사해 입력해주세요.").append("<br><br>");

        if(signUpCheck) {
            sb.append("회원가입 메일 인증 코드: ");
        } else {
            sb.append("임시 비밀번호 재발급 코드: ");
        }

        sb.append(key);

        return sb.toString();
    }

    private String createKey() {
        StringBuilder key = new StringBuilder();
        Random random = new Random();

        for(int i=0; i<8; i++) {
            int index = random.nextInt(3);   // 0 ~ 2까지 랜덤

            switch (index) {
                case 0:
                    key.append((char) ((int) (random.nextInt(26)) + 97));
                case 1:
                    key.append((char) ((int) (random.nextInt(26)) + 65));
                case 2:
                    key.append((random.nextInt(10)));
                    break;
            }
        }
        return key.toString();
    }
}
