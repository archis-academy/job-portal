package com.archisacademy.jobportal.loggers.messages;

public final class JobAppMessage {

    private JobAppMessage() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static final String JOB_NOT_FOUND = "Job not found with ID: ";
    public static final String JOB_CREATED_SUCCESS = "Job created successfully";
    public static final String JOB_UPDATED_SUCCESS = "Job updated successfully";
    public static final String JOB_DELETED_SUCCESS = "Job deleted successfully";
    public static final String JOB_ALREADY_APPLIED = "User has already applied to this job";
    public static final String JOB_ALREADY_EXISTS = "Job already exists.";
    public static final String JOB_HAS_APPLICATIONS = "Job has applications.";
    public static final String INVALID_JOB_DETAILS = "Invalid job details.";
    public static final String JOB_APPLIED_SUCCESS = "Job applied successfully.";
    public static final String JOB_USER_NOT_FOUND = "User cannot be null in Job entity for job ID: ";

}
