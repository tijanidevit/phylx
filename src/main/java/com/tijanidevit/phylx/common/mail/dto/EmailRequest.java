package com.tijanidevit.phylx.common.mail.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmailRequest {
    private String to;
    private String subject;
    private String body;

    @Builder.Default
    private final boolean html = true;
}
