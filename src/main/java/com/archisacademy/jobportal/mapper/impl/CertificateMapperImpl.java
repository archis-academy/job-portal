package com.archisacademy.jobportal.mapper.impl;

import com.archisacademy.jobportal.dto.CertificateDto;
import com.archisacademy.jobportal.loggers.MainLogger;
import com.archisacademy.jobportal.mapper.CertificateMapper;
import com.archisacademy.jobportal.model.Certificate;
import com.archisacademy.jobportal.repositories.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;


@Component
public class CertificateMapperImpl implements CertificateMapper {
    private final ProfileRepository profileRepository;
    private final static MainLogger LOGGER = new MainLogger(CertificateMapperImpl.class);

    public CertificateMapperImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

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
                .profile(profileRepository.findById(certificateDto.getProfileId())
                        .orElseThrow(() -> {
                            LOGGER.log("Profile not found", HttpStatus.NOT_FOUND);
                            return null;
                        }))
                .build();
    }
}
