package com.archisacademy.jobportal.loggers.messages;

public final class SkillsMessage {

    public static final String SKILLS_NOT_FOUND = "Skill not found with id: ";
    public static final String SKILL_DELETED = "Skill deleted successfully with this id: ";
    public static final String SKILL_UPDATED = "Skill updated successfully with this id: ";
    public static final String SKILL_CREATED = "Skill created successfully with this id: ";
    private SkillsMessage() {
        throw new IllegalStateException("Utility class can not be instantiated!");
    }
}
