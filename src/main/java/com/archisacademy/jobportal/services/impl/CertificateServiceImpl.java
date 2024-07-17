package com.archisacademy.jobportal.services.impl;


import com.archisacademy.jobportal.dto.CertificateDto;
import com.archisacademy.jobportal.loggers.MainLogger;
import com.archisacademy.jobportal.loggers.messages.CertificateMessage;
import com.archisacademy.jobportal.loggers.messages.ProfilesMessage;
import com.archisacademy.jobportal.mapper.CertificateMapper;
import com.archisacademy.jobportal.model.Certificate;
import com.archisacademy.jobportal.model.Profile;
import com.archisacademy.jobportal.repositories.CertificateRepository;
import com.archisacademy.jobportal.repositories.ProfileRepository;
import com.archisacademy.jobportal.services.CertificateService;
import com.archisacademy.jobportal.services.ProfileService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CertificateServiceImpl implements CertificateService {
    private final CertificateRepository certificateRepository;
    private final ProfileService profileService;
    private final CertificateMapper certificateMapper;
    private final MainLogger LOGGER = new MainLogger(CertificateService.class);

    public CertificateServiceImpl(CertificateRepository certificateRepository, ProfileService profileService, CertificateMapper certificateMapper) {
        this.certificateRepository = certificateRepository;
        this.profileService = profileService;
        this.certificateMapper = certificateMapper;
    }

    @Override
    @Transactional
    public String createCertificate(CertificateDto certificateDto) {
        Certificate certificate = certificateMapper.toEntity(certificateDto);
        certificate.setProfile(profileService.getProfileEntityById(certificateDto.getProfileId()));
        certificateRepository.save(certificate);
        return CertificateMessage.CERTIFICATE_CREATED + certificate.getId();
    }

    @Override
    public CertificateDto getCertificateById(long id) {
        return certificateMapper.toDto(findCertificateById(id));
    }

    @Override
    @Transactional
    public String updateCertificate(long id, CertificateDto certificateDto) {
        Certificate certificate = findCertificateById(id);
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
        profileService.getProfileEntityById(profileId);

        return certificateRepository.findByProfileId(profileId).stream()
                .map(certificateMapper::toDto)
                .collect(Collectors.toList());
    }

    private Certificate findCertificateById(long id) {
        return certificateRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.log(CertificateMessage.CERTIFICATE_NOT_FOUND + id, HttpStatus.NOT_FOUND);
                    return null;
                });
    }



}
