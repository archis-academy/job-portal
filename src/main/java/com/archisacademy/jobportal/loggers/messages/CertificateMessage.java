package com.archisacademy.jobportal.loggers.messages;


public final class CertificateMessage {

    private CertificateMessage(){
        throw new UnsupportedOperationException("Utility class can not be instantiated");
    }

    public static final String CERTIFICATE_CREATED = "Certificate has been created successfully with id: ";
    public static final String CERTIFICATE_DELETED = "Certificate has been deleted successfully with id: ";
    public static final String CERTIFICATE_UPDATED = "Certificate has been updated successfully with id: ";
    public static final String CERTIFICATE_NOT_FOUND = "Certificate not found with id: ";
}
