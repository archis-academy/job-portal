package com.archisacademy.jobportal.loggers.messages;

public final class ProfilesMessage {
    public static final String PROFILE_NOT_FOUND = "Profile not found with id: ";
    public static final String PROFILE_DELETED = "Profile deleted successfully with this id: ";
    public static final String PROFILE_UPDATED = "Profile updated successfully with this id: ";
    public static final String PROFILE_CREATED = "Profile created successfully with this id: ";
    private ProfilesMessage() {
        throw new IllegalStateException("Utility class can not be instantiated!");
    }
}
