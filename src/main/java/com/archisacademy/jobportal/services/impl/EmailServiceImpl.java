package com.archisacademy.jobportal.services.impl;

import com.archisacademy.jobportal.dto.responses.EmailDetails;
import com.archisacademy.jobportal.loggers.MainLogger;
import com.archisacademy.jobportal.loggers.messages.EmailMessage;
import com.archisacademy.jobportal.services.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService {
    private final static MainLogger LOGGER = new MainLogger(EmailServiceImpl.class);
    private JavaMailSender javaMailSender;

    @Override
    public String sendEmail(EmailDetails emailDetails, String email) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(emailDetails.getSender());
            mailMessage.setTo(email);
            mailMessage.setSubject(emailDetails.getSubject());
            mailMessage.setText(emailDetails.getBody());

            javaMailSender.send(mailMessage);
            System.out.println("Email sent successfully to " + email);
        } catch (Exception e) {
            LOGGER.log(EmailMessage.EMAIL_SENDING_FAILED+ " " + e.getMessage());
            return null;
        }
        return EmailMessage.EMAIL_SENT_SUCCESSFULLY;
    }

}
