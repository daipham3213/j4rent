package io.tomcode.j4rent.core.services;

import org.springframework.stereotype.Component;

@Component
public interface IEmailService {
    void sendEmail(String to, String subject, String text);
}
