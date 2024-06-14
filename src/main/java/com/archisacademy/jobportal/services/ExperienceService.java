package com.archisacademy.jobportal.services;

import com.archisacademy.jobportal.dto.ExperienceDto;
import org.springframework.data.domain.Page;

public interface ExperienceService {

    String createExperience(ExperienceDto experienceDto);

    String deleteExperience(Long id);

    String updateExperience(long experienceId, ExperienceDto experienceDto);

    Page<ExperienceDto> getAllExperiences(int pageNo, int pageSize);

    ExperienceDto getExperienceById(Long id);
}
