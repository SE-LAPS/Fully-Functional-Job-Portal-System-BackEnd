package com.example.Admin_Dashboard_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity class representing a job position.
 * Maps to the 'job_position' table in the database.
 */
@Getter
@Setter
@Entity
public class JobPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String positionTitle;
    private String category;
    private String salaryRange;
    private String jobType;
    private String applicationLinkOrEmail;
    private String positionDescription;
    private String companyName;
    private String companyWebsite;
    private String emailAddress;
    private String companyDescription;
    private String location;
}
