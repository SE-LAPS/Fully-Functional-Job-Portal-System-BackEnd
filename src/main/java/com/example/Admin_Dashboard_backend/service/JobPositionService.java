package com.example.Admin_Dashboard_backend.service;


import com.example.Admin_Dashboard_backend.model.JobPosition;


import java.util.List;
import java.util.Optional;

public interface JobPositionService {
    List<JobPosition> getAllJobPositions();
    Optional<JobPosition> getJobPositionById(Integer id);
    JobPosition createJobPosition(JobPosition jobPosition);
    JobPosition updateJobPosition(JobPosition jobPosition);
    boolean deleteJobPosition(Integer id);

    JobPosition saveJobPosition(JobPosition jobPosition);

    Optional<JobPosition> getJobPositionById(Long id);

    JobPosition updateJobPosition(Long id, JobPosition jobPosition);

    void deleteJobPosition(Long id);
}