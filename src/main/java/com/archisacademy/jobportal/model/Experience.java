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
@Table(name = "experiences")
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "is_active")
    private boolean isActive;
    @Column(name = "start_date")
    private Timestamp startDate;
    @Column(name = "end_date")
    private Timestamp endDate;
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
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

}
