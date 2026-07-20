package com.tijanidevit.phylx.common.mail;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class EmailTemplateService {

    public String render(EmailTemplate template, Map<String, String> variables) {

        String html = loadTemplate(template.getFileName());

        for (Map.Entry<String, String> entry : variables.entrySet()) {
            html = html.replace(
                    "{{" + entry.getKey() + "}}",
                    entry.getValue()
            );
        }

        return html;
    }

    private String loadTemplate(String template) {
        try (InputStream inputStream = new ClassPathResource("templates/mail/" + template).getInputStream()) {

            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

        } catch (IOException exception) {
            throw new RuntimeException(
                    "Unable to load email template: " + template,
                    exception
            );
        }
    }
}
