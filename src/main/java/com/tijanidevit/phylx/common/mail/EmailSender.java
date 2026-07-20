package com.tijanidevit.phylx.common.mail;

import org.springframework.scheduling.annotation.Async;

import com.tijanidevit.phylx.common.mail.dto.EmailRequest;

public interface EmailSender {
    @Async
    void send(EmailRequest request);
}