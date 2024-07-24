package com.archisacademy.jobportal.loggers.messages;

public final class ProjectMessage {

    private ProjectMessage() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static final String PROJECT_NOT_FOUND = "Project not found with id: ";
    public static final String PROJECT_CREATED_SUCCESS = "Project created successfully";
    public static final String PROJECT_DELETED_SUCCESS = "Project deleted successfully";
    public static final String PROJECT_UPDATED_SUCCESS = "Project updated successfully";
    public static final String PROJECT_DELETED_LOG = "Project with id %d deleted successfully.";
}
