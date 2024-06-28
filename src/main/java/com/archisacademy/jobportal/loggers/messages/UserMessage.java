package com.archisacademy.jobportal.loggers.messages;

public final class UserMessage {

    private UserMessage() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static final String USER_NOT_FOUND = "User not found with UUID: ";
    public static final String USER_CREATED_SUCCESS = "User created successfully";
    public static final String USER_DELETED_SUCCESS = "User deleted successfully";
    public static final String USER_UPDATED_SUCCESS = "User updated successfully";
    public static final String USER_DELETED_LOG = "User with UUID %s deleted successfully.";
    public static final String UNAUTHORIZED_ACTION = "Unauthorized action by user with UUID: ";
}
