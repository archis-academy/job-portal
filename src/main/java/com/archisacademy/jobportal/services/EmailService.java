package com.archisacademy.jobportal.services;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
}
