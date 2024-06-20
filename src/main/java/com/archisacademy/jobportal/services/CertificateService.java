package com.archisacademy.jobportal.services;

import com.archisacademy.jobportal.dto.CertificateDto;

public interface CertificateService {
    String createCertificate(CertificateDto certificateDto);
    CertificateDto getCertificateById(long id);
    String updateCertificate(long id, CertificateDto certificateDto);
    String deleteCertificate(long id);
    String getAllCertificateByProfileId(long profileId);


}
