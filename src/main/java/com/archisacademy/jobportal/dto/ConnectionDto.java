package com.archisacademy.jobportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConnectionDto {
    private String status;
    private String uuid;
    private Timestamp requestDate;

}
