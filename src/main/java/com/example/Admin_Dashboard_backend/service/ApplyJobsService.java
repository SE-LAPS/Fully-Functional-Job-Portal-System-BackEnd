package com.example.Admin_Dashboard_backend.service;

import com.example.Admin_Dashboard_backend.model.ApplyJobsModel;

import java.util.List;
import java.util.Map;

/**
 * Service interface for managing job applications.
 */
public interface ApplyJobsService {

    /**
     * Submits a new job application.
     *
     * @param applyJobsModel the job application details.
     * @return the saved job application.
     */
    ApplyJobsModel applyForJob(ApplyJobsModel applyJobsModel);

    /**
     * Retrieves all job applications.
     *
     * @return a list of all job applications.
     */
    List<ApplyJobsModel> getAllApplications();

    /**
     * Retrieves a job application by its ID.
     *
     * @param id the ID of the job application.
     * @return the job application, or null if not found.
     */
    ApplyJobsModel getApplicationById(Long id);

    /**
     * Updates an existing job application.
     *
     * @param applyJobsModel the updated job application details.
     * @return the updated job application.
     */
    ApplyJobsModel updateApplication(ApplyJobsModel applyJobsModel);

    /**
     * Deletes a job application by its ID.
     *
     * @param id the ID of the job application to delete.
     */
    void deleteApplication(Long id);

    /**
     * Retrieves the count of applications grouped by job title.
     *
     * @return a map of job titles and their corresponding application counts.
     */
    Map<String, Integer> getApplicationCountByJobTitle();
}
