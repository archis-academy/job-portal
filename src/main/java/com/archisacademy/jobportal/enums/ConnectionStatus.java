package com.archisacademy.jobportal.enums;

import java.util.stream.Stream;

public enum ConnectionStatus {
    PENDING("pending"),
    ACCEPTED("accepted"),
    DECLINED("declined"),
    BLOCKED("blocked");
    private String description;

    ConnectionStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static ConnectionStatus value(String description) {
        return Stream.of(ConnectionStatus.values())
                .filter(x -> x.getDescription().equals(description))
                .findFirst()
                .orElse(null);
    }
}