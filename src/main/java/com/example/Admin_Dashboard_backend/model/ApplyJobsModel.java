package com.example.Admin_Dashboard_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Entity
public class ApplyJobsModel {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String resumeFilePath;
    private String companyName;
    private String positionTitle;

    // Constructors, getters, and setters
    public ApplyJobsModel() {
    }

    public ApplyJobsModel(String name, String email, String resumeFilePath, String companyName, String positionTitle) {
        this.name = name;
        this.email = email;
        this.resumeFilePath = resumeFilePath;
        this.companyName = companyName;
        this.positionTitle = positionTitle;
    }
}
