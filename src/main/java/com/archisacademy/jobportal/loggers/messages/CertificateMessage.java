package com.archisacademy.jobportal.loggers.messages;


public final class CertificateMessage {

    private CertificateMessage(){
        throw new UnsupportedOperationException("Utility class can not be instantiated");
    }

    public static final String CERTIFICATE_CREATED = "Certificate has been created successfully with uuid: ";
}
