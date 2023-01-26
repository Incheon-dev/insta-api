package com.insta.instaapi.verification.service;

import com.insta.instaapi.user.dto.request.FindRequest;
import com.insta.instaapi.user.service.UserServiceImpl;
import com.insta.instaapi.utils.redis.RedisUtil;
import com.insta.instaapi.verification.dto.request.VerificationRequest;
import com.insta.instaapi.verification.utils.EmailConstructor;
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
    private final UserServiceImpl userService;

    @Override
    public void sendVerificationNumber(String email) {
        EmailConstructor constructor = create();
        sendEmail(email, constructor.getAuthKey(), constructor.getSubject(), constructor.getText());
    }

    @Override
    public Boolean verify(VerificationRequest request) {
        return Objects.equals(request.getEmail(), redisUtil.getData(request.getAuthKey()));
    }

    @Override
    public void existEmail(FindRequest request) {
        userService.existsByEmailAndPhoneNumberAndName(request.getEmail(), request.getPhoneNumber(), request.getName());
        EmailConstructor constructor = create();
        sendEmail(request.getEmail(), constructor.getAuthKey(), constructor.getSubject(), constructor.getText());
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

    private EmailConstructor create() {
        Random random = new Random();
        String authKey = String.valueOf(random.nextInt(888888) + 111111);
        String subject = "인스타그램 인증번호입니다.";
        String text = "인증번호는 " + authKey + "입니다. <br/>";

        return EmailConstructor.builder()
                .authKey(authKey)
                .subject(subject)
                .text(text)
                .build();
    }
}
