package com.archisacademy.jobportal.services;

import com.archisacademy.jobportal.model.Skills;

import java.util.List;

public interface SkillsService {
    String addSkill(Skills skill);
    void deleteSkill(String skillId);
    String updateSkill(String skillId, Skills skill);
    List<Skills> listSkills();
    Skills searchSkill(long skillId);
}
