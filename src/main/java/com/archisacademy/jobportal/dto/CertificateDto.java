package com.archisacademy.jobportal.dto;

import com.archisacademy.jobportal.loggers.messages.ValidationWarnings;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CertificateDto {
    @NotEmpty(message = ValidationWarnings.NAME_IS_REQUIRED)
    private String name;
    @NotEmpty(message = ValidationWarnings.COMPANY_NAME_IS_REQUIRED)
    private String companyName;
    private Timestamp postingDate;
    private int hours;
    private String url;
    private long profileId;

}
