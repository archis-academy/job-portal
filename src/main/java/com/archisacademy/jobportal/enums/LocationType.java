package com.archisacademy.jobportal.enums;

import java.util.stream.Stream;

public enum LocationType {
    ONSITE("onsite"),
    REMOTE("remote"),
    HYBRID("hybrid");

    private String description;

    LocationType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static LocationType value(String description) {
        return Stream.of(LocationType.values())
                .filter(x -> x.getDescription().equals(description))
                .findFirst()
                .orElse(null);
    }
}