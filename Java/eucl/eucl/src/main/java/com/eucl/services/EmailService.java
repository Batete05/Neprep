package com.eucl.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class EmailService {

    private JavaMailSender mailSender;

    public void sendEmail(String email, String mesage) {
        try {
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("info@bussines.com");
            helper.setTo(email);
            helper.setSubject("Token Expiration Reminder");

            String htmlContent = """
                <h1 style="color: #007bff;">Token Expiration Reminder</h1>
                <p>%s</p>
                """.formatted(mesage);

            helper.setText(htmlContent, true);

            mailSender.send(message);
        } catch (MessagingException exception) {
            log.error(exception.getMessage());
        }

    }
}
