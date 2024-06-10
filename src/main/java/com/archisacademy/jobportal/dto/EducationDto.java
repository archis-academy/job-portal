package com.archisacademy.jobportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EducationDto {
    private String university;
    private String department;
    private Timestamp graduationDate;
    private String description;
    private Timestamp startDate;
    private Long profileId;
}
