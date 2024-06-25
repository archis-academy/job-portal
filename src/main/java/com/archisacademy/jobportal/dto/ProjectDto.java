package com.archisacademy.jobportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDto {
    private String projectName;
    private Timestamp startDate;
    private Timestamp endDate;
    private String position;
    private Boolean status;
    private String url;
    private List<String> technologies;
    private String description;
    private Long profileId;

}
