package com.example.Admin_Dashboard_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity class representing a job application.
 */
@Getter
@Setter
@Entity
public class ApplyJobsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String companyName;
    private String positionTitle;

    /**
     * Default constructor.
     */
    public ApplyJobsModel() {
    }

    /**
     * Parameterized constructor to initialize all fields.
     *
     * @param name          the name of the applicant.
     * @param email         the email of the applicant.
     * @param companyName   the name of the company.
     * @param positionTitle the title of the position being applied for.
     */
    public ApplyJobsModel(String name, String email, String companyName, String positionTitle) {
        this.name = name;
        this.email = email;
        this.companyName = companyName;
        this.positionTitle = positionTitle;
    }
}
