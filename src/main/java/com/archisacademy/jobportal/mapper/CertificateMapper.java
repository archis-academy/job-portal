package com.archisacademy.jobportal.mapper;

import com.archisacademy.jobportal.dto.CertificateDto;
import com.archisacademy.jobportal.model.Certificate;

public interface CertificateMapper {
    CertificateDto toDto(Certificate certificate);
    Certificate toEntity(CertificateDto certificateDto);
}
