package com.archisacademy.jobportal.loggers.messages;

public final class EducationMessage {

    private EducationMessage() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static final String PROFILE_NOT_FOUND = "Profile not found with id: ";
    public static final String EDUCATION_NOT_FOUND = "Education not found with id: ";
    public static final String GRADUATION_DATE_BEFORE_START_DATE = "Graduation date cannot be before start date.";
    public static final String EDUCATION_CREATED_SUCCESS = "Education created successfully";
    public static final String EDUCATION_DELETED_SUCCESS = "Education deleted successfully";
    public static final String EDUCATION_UPDATED_SUCCESS = "Education updated successfully";
}
