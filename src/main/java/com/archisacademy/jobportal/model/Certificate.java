package com.archisacademy.jobportal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "certificates")
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "certificate_name")
    private String certificateName;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "posting_date")
    private LocalDate postingDate;

    @Column(name = "certificate_hours")
    private int certificateHours;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

}
