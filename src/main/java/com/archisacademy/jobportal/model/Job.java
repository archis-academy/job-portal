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
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "created_date")
    private Timestamp createdDate;
    @Column(name = "location")
    private String location;
    @Enumerated(EnumType.STRING)
    @Column(name = "location_type")
    private LocationType locationType;
    @Column(name = "position")
    private String position;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
