package com.archisacademy.jobportal.services.impl;

import com.archisacademy.jobportal.loggers.MainLogger;
import com.archisacademy.jobportal.loggers.messages.EmailMessage;
import com.archisacademy.jobportal.services.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    private final static MainLogger LOGGER = new MainLogger(EmailServiceImpl.class);
    @Value("${spring.mail.verify.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String fromEmail;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    @Override
    public void sendEmail(String to, String subject, String body) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(EmailMessage.NEW_USER_ACCOUNT_VERIFICATION);
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setText(body);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
