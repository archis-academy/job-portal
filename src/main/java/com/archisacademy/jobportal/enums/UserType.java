package com.archisacademy.jobportal.enums;

import java.util.stream.Stream;

public enum UserType {
    ADMIN("admin"), // TODO: 24.05.2024  be a refactor to COMPANY
    USER("user");
    private String description;

    UserType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static UserType value(String description) {
        return Stream.of(UserType.values())
                .filter(x -> x.getDescription().equals(description))
                .findFirst()
                .orElse(null);
    }
}