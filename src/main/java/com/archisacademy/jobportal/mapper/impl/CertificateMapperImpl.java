package com.archisacademy.jobportal.mapper.impl;

import com.archisacademy.jobportal.dto.CertificateDto;
import com.archisacademy.jobportal.loggers.MainLogger;
import com.archisacademy.jobportal.mapper.CertificateMapper;
import com.archisacademy.jobportal.model.Certificate;
import com.archisacademy.jobportal.repositories.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;


@Component
public class CertificateMapperImpl implements CertificateMapper {
    private final static MainLogger LOGGER = new MainLogger(CertificateMapperImpl.class);


    @Override
    public CertificateDto toDto(Certificate certificate) {
        return CertificateDto.builder()
                .name(certificate.getCertificateName())
                .companyName(certificate.getCompanyName())
                .postingDate(certificate.getPostingDate())
                .hours(certificate.getCertificateHours())
                .url(certificate.getCertificateUrl())
                .profileId(certificate.getProfile().getId())
                .build();
    }

    @Override
    public Certificate toEntity(CertificateDto certificateDto) {
        return Certificate.builder()
                .certificateName(certificateDto.getName())
                .companyName(certificateDto.getCompanyName())
                .postingDate(new Timestamp(System.currentTimeMillis()))
                .certificateHours(certificateDto.getHours())
                .certificateUrl(certificateDto.getUrl())
                .build();
    }

    @Override
    public List<CertificateDto> toCertificateDtos(List<Certificate> certificates) {
        return certificates.stream()
                .map(this::toDto)
                .toList();
    }
}
