package com.tijanidevit.phylx.common.mail;

public enum EmailTemplate {
    
    VERIFICATION("verification-email.html"),
    PASSWORD_RESET("password-reset-email.html"),
    WELCOME("welcome-email.html");

    private final String fileName;

    EmailTemplate(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    
}
