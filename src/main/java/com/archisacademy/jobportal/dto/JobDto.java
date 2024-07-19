package com.archisacademy.jobportal.dto;

import com.archisacademy.jobportal.enums.LocationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobDto {
    private String companyName;
    private Timestamp startDate;
    private Timestamp endDate;
    private String location;
    private String locationType;
    private String position;
    private String description;
    private String userUuid;
}
