package com.archisacademy.jobportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileDto {
    private Long id;
    private String sector;
    private Timestamp birthDate;
    private String summary;
    private Long userId;
    private List<CertificateDto> certificates;
    private List<SkillDto> skills;
    private List<ExperienceDto> experiences;
    private List<EducationDto> educations;
    private List<ProjectDto> projects;

}
