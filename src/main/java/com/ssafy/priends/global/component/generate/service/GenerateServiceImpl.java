package com.ssafy.priends.global.component.generate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class GenerateServiceImpl implements GenerateService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Async("threadPoolTaskExecutor")
    @Override
    public void sendMailAlarm(String title, String contents, String receiver, String template) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            // 메일 제목 설정
            helper.setSubject(title);

            // 수신자 설정
            helper.setTo(receiver);

            HashMap<String, String> emailValues = new HashMap<>();
            emailValues.put("title", title);
            emailValues.put("content", contents);

            Context context = new Context();
            emailValues.forEach((key, value) -> {
                context.setVariable(key, value);
            });

            String html = templateEngine.process(template, context);
            helper.setText(html, true);
            
            // 메일 보내기
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
