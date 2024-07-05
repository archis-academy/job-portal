package com.archisacademy.jobportal.controller;

import com.archisacademy.jobportal.dto.CertificateDto;
import com.archisacademy.jobportal.services.CertificateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    private final CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @PostMapping
    public ResponseEntity<String> createCertificate(@RequestBody CertificateDto certificateDto) {
        return new ResponseEntity<>(certificateService.createCertificate(certificateDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCertificate(@PathVariable Long id, @RequestBody CertificateDto certificateDto) {
        return new ResponseEntity<>(certificateService.updateCertificate(id, certificateDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCertificate(@PathVariable Long id) {
        return new ResponseEntity<>(certificateService.deleteCertificate(id), HttpStatus.OK);
    }

    @GetMapping("/profile/{profileId}")
    public ResponseEntity<List<CertificateDto>> getCertificateByProfileId(@PathVariable Long profileId) {
        return new ResponseEntity<>(certificateService.getAllCertificateByProfileId(profileId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CertificateDto> getCertificateById(@PathVariable Long id) {
        return new ResponseEntity<>(certificateService.getCertificateById(id), HttpStatus.OK);
    }
}
