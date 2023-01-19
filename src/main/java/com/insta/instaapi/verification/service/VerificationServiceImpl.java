package com.insta.instaapi.verification.service;

import com.insta.instaapi.utils.redis.RedisUtil;
import com.insta.instaapi.verification.dto.request.VerificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Objects;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class VerificationServiceImpl implements VerificationService {

    private final JavaMailSender javaMailSender;
    private final RedisUtil redisUtil;

    @Override
    public void sendVerificationNumber(String email) {
        Random random = new Random();
        String authKey = String.valueOf(random.nextInt(888888) + 111111);
        String subject = "인스타그램 인증번호입니다.";
        String text = "인증번호는 " + authKey + "입니다. <br/>";

        sendEmail(email, authKey, subject, text);
    }

    @Override
    public Boolean verify(VerificationRequest request) {
        return Objects.equals(request.getEmail(), redisUtil.getData(request.getAuthKey()));
    }

    private void sendEmail(String email, String authKey, String subject, String text) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(text, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        redisUtil.setDataExpire(authKey, email, 60 * 5L);
    }
}
