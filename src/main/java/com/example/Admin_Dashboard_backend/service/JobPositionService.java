package com.example.Admin_Dashboard_backend.service;

import com.example.Admin_Dashboard_backend.model.JobPosition;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing job positions.
 */
public interface JobPositionService {
    /**
     * Retrieves all job positions.
     *
     * @return a list of all job positions
     */
    List<JobPosition> getAllJobPositions();

    /**
     * Retrieves a job position by its ID.
     *
     * @param id the ID of the job position to retrieve
     * @return an Optional containing the job position if found, or an empty Optional if not found
     */
    Optional<JobPosition> getJobPositionById(Integer id);

    /**
     * Creates a new job position.
     *
     * @param jobPosition the job position to create
     * @return the created job position
     */
    JobPosition createJobPosition(JobPosition jobPosition);

    /**
     * Updates an existing job position.
     *
     * @param jobPosition the job position data to update
     * @return the updated job position
     */
    JobPosition updateJobPosition(JobPosition jobPosition);

    /**
     * Deletes a job position by its ID.
     *
     * @param id the ID of the job position to delete
     * @return true if the deletion was successful, false otherwise
     */
    boolean deleteJobPosition(Integer id);

    /**
     * Saves a job position to the database.
     *
     * @param jobPosition the job position to save
     * @return the saved job position
     */
    JobPosition saveJobPosition(JobPosition jobPosition);

    /**
     * Retrieves a job position by its ID.
     *
     * @param id the ID of the job position to retrieve
     * @return an Optional containing the job position if found, or an empty Optional if not found
     */
    Optional<JobPosition> getJobPositionById(Long id);

    /**
     * Updates an existing job position by its ID.
     *
     * @param id the ID of the job position to update
     * @param jobPosition the job position data to update
     * @return the updated job position, or null if the job position does not exist
     */
    JobPosition updateJobPosition(Long id, JobPosition jobPosition);

    /**
     * Deletes a job position by its ID.
     *
     * @param id the ID of the job position to delete
     */
    void deleteJobPosition(Long id);
}
