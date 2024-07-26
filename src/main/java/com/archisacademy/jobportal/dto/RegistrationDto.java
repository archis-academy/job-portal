package com.archisacademy.jobportal.dto;

import com.archisacademy.jobportal.loggers.messages.ValidationWarnings;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {

    @NotEmpty(message = ValidationWarnings.NAME_IS_REQUIRED)
    private String username;
    @NotEmpty(message = ValidationWarnings.PASSWORD_IS_REQUIRED)
    private String password;
    private String dob;
    @NotEmpty(message = ValidationWarnings.EMAIL_IS_REQUIRED)
    private String email;
    private String phone;
    @NotEmpty(message = ValidationWarnings.IDENTITY_NUMBER_IS_REQUIRED)
    private String identityNumber;
}
