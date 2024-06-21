package com.archisacademy.jobportal.services.impl;


import com.archisacademy.jobportal.dto.CertificateDto;
import com.archisacademy.jobportal.loggers.MainLogger;
import com.archisacademy.jobportal.loggers.messages.CertificateMessage;
import com.archisacademy.jobportal.loggers.messages.ProfilesMessage;
import com.archisacademy.jobportal.mapper.CertificateMapper;
import com.archisacademy.jobportal.model.Certificate;
import com.archisacademy.jobportal.repositories.CertificateRepository;
import com.archisacademy.jobportal.repositories.ProfileRepository;
import com.archisacademy.jobportal.services.CertificateService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CertificateServiceImpl implements CertificateService {
    private final CertificateRepository certificateRepository;
    private final ProfileRepository profileRepository;
    private final CertificateMapper certificateMapper;
    private final MainLogger LOGGER = new MainLogger(CertificateService.class);

    public CertificateServiceImpl(CertificateRepository certificateRepository, ProfileRepository profileRepository, CertificateMapper certificateMapper) {
        this.certificateRepository = certificateRepository;
        this.profileRepository = profileRepository;
        this.certificateMapper = certificateMapper;
    }

    @Override
    @Transactional
    public String createCertificate(CertificateDto certificateDto) {
        Certificate certificate = certificateMapper.toEntity(certificateDto);
        certificateRepository.save(certificate);
        return CertificateMessage.CERTIFICATE_CREATED + certificate.getId();
    }

    @Override
    public CertificateDto getCertificateById(long id) {
        return certificateRepository.findById(id)
                .map(certificateMapper::toDto)
                .orElseThrow(() -> {
                    LOGGER.log(ProfilesMessage.PROFILE_NOT_FOUND, HttpStatus.NOT_FOUND);
                    return null;
                });
    }

    @Override
    @Transactional
    public String updateCertificate(long id, CertificateDto certificateDto) {
        Certificate certificate = certificateRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.log(CertificateMessage.CERTIFICATE_NOT_FOUND + id, HttpStatus.NOT_FOUND);
                    return null;
                });
        certificate.setCertificateName(certificateDto.getName());
        certificate.setCompanyName(certificateDto.getCompanyName());
        certificate.setCertificateHours(certificateDto.getHours());
        certificate.setCertificateUrl(certificateDto.getUrl());
        certificateRepository.save(certificate);

        return CertificateMessage.CERTIFICATE_UPDATED + id;
    }

    @Override
    @Transactional
    public String deleteCertificate(long id) {
        certificateRepository.deleteById(id);
        return CertificateMessage.CERTIFICATE_DELETED + id;
    }

    @Override
    public List<CertificateDto> getAllCertificateByProfileId(long profileId) {
        profileRepository.findById(profileId)
                .orElseThrow(() -> {
                    LOGGER.log(ProfilesMessage.PROFILE_NOT_FOUND, HttpStatus.NOT_FOUND);
                    return null;
                });

        return certificateRepository.findByProfileId(profileId).stream()
                .map(certificateMapper::toDto)
                .collect(Collectors.toList());
    }

}
