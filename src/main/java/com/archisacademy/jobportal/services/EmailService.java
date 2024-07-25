package com.archisacademy.jobportal.services;

import com.archisacademy.jobportal.dto.responses.EmailDetails;

public interface EmailService {
    String sendEmail(EmailDetails emailDetails,String email);
}
