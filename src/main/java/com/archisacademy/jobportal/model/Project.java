package com.archisacademy.jobportal.model;

import com.archisacademy.jobportal.enums.LocationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "project_name")
    private String projectName;
    @Column(name = "start_date")
    private Timestamp startDate;
    @Column(name = "end_date")
    private Timestamp endDate;
    @Column(name = "position")
    private String position;
    @Column(name = "url")
    private String url;
    @Column(name = "technologies")
    private String technologies;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

}