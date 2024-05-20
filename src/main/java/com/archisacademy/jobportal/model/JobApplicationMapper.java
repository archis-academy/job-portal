package com.archisacademy.jobportal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "job_application_mappers")
public class JobApplicationMapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToMany(mappedBy = "jobApplications", cascade = CascadeType.ALL)
    private List<User> users;
    @OneToOne
    @JoinColumn(name = "job_id")
    private Job job;

}
