package com.archisacademy.jobportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    private String projectName;
    private Timestamp startDate;
    private Timestamp endDate;
    private String position;
    private Boolean status;
    private String url;
    private String technologies;
    private String description;

}
