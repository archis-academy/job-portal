package com.archisacademy.jobportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificateDto {
    private String name;
    private String companyName;
    private Timestamp postingDate;
    private int hours;
    private String url;

}
