package com.tijanidevit.phylx.common.mail.provider;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.tijanidevit.phylx.common.mail.EmailSender;
import com.tijanidevit.phylx.common.mail.dto.EmailRequest;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SmtpEmailSender implements EmailSender {

    private final JavaMailSender mailSender;

    // private String from = "info@phylx.com";

    @Async
    public void send(EmailRequest request) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            
            helper.setTo(request.getTo());
            helper.setSubject(request.getSubject());
            helper.setText(request.getBody(), request.isHtml());
            mailSender.send(message);
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
