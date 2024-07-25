package com.archisacademy.jobportal.services.impl;

import com.archisacademy.jobportal.dto.responses.EmailDetails;
import com.archisacademy.jobportal.loggers.MainLogger;
import com.archisacademy.jobportal.loggers.messages.EmailMessage;
import com.archisacademy.jobportal.services.EmailService;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.flywaydb.core.internal.resource.filesystem.FileSystemResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

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
