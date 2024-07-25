package com.archisacademy.jobportal.loggers.messages;

public class EmailMessage {
    public static final String EMAIL_SENT_SUCCESSFULLY = "Email sent successfully";
    public static final String EMAIL_SENDING_FAILED = "Email sending failed";


    private EmailMessage() {
        throw new IllegalStateException("Utility class can not be instantiated!");
    }
}
