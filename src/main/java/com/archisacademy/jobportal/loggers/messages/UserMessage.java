package com.archisacademy.jobportal.loggers.messages;

public final class UserMessage {

    private UserMessage() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static final String USER_NOT_FOUND = "User not found with ID: ";
    public static final String USER_CREATED_SUCCESS = "User created successfully";
    public static final String USER_DELETED_SUCCESS = "User deleted successfully";
    public static final String USER_UPDATED_SUCCESS = "User updated successfully";
    public static final String UNAUTHORIZED_ACTION = "Unauthorized action by user with UUID: ";
    public static final String USER_NOT_FOUND_BY_UUID = "User not found with UUID: ";
    public static final String USER_NOT_FOUND_BY_CONNECTION_UUID = "Connection not found with UUID: ";
    public static final String USERS_NOT_FOUND_BY_ROLE = "Users not found with role: %s";
    public static final String CANNOT_CONNECT_WITH_SELF = "User cannot connect with themselves.";
    public static final String CONNECTION_ALREADY_EXISTS = "Connection already exists.";
    public static final String CONNECTION_NOT_FOUND = "Connection not found with UUID: ";
}
