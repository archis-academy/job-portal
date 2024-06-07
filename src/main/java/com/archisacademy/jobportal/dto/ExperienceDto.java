package com.archisacademy.jobportal.dto;
import com.archisacademy.jobportal.enums.LocationType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceDto {
    private String companyName;
    private boolean isActive;
    private Timestamp startDate;
    private Timestamp endDate;
    private String location;
    private String locationType;
    private String position;
    private String description;
    private List<SkillsDto> usedSkills;

}