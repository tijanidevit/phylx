package com.tijanidevit.phylx.common.mail;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.tijanidevit.phylx.common.mail.dto.EmailRequest;
import com.tijanidevit.phylx.user.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService implements EmailSender {
    private final EmailSender emailSender;
    private final EmailTemplateService templateService;

    @Override
    public void send(EmailRequest request) {
        emailSender.send(request);
    }


    public void sendVerificationEmail(User user, String code) {
        String body = templateService.render(EmailTemplate.VERIFICATION, Map.of(
            "name", user.getName(),
            "code", code
        ));


        EmailRequest request = EmailRequest.builder()
                .to(user.getEmail())
                .subject("Email Verification")
                .body(body)
                .build();

        send(request);
    }
}
