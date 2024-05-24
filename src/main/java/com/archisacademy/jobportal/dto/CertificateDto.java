package com.archisacademy.jobportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificateDto {
    private String certificateName;
    private String companyName;
    private Timestamp postingDate;
    private int certificateHours;
    private String certificateUrl;

}
