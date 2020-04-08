package com.rcore.domain.userPasswordRecover.port;

public interface EmailSender {
    public void send(String toEmail, String code);
}
