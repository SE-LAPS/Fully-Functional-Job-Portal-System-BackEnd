package com.example.Admin_Dashboard_backend.controller;

import com.example.Admin_Dashboard_backend.model.JobPosition;
import com.example.Admin_Dashboard_backend.service.JobPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing job positions.
 * Provides endpoints for creating, updating, deleting, and fetching job positions.
 */
@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")
public class JobPositionController {

    private final JobPositionService jobPositionService;

    /**
     * Constructs a new JobPositionController with the provided JobPositionService.
     *
     * @param jobPositionService the service used to manage job positions
     */
    @Autowired
    public JobPositionController(JobPositionService jobPositionService) {
        this.jobPositionService = jobPositionService;
    }

    /**
     * Retrieves a list of all job positions.
     *
     * @return a list of all job positions
     */
    @GetMapping
    public List<JobPosition> getAllJobPositions() {
        return jobPositionService.getAllJobPositions();
    }

    /**
     * Retrieves a job position by its ID.
     *
     * @param id the ID of the job position to retrieve
     * @return the job position with the specified ID, or a 404 Not Found response if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<JobPosition> getJobPositionById(@PathVariable Long id) {
        Optional<JobPosition> jobPosition = jobPositionService.getJobPositionById(id);
        return jobPosition.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Creates a new job position.
     *
     * @param jobPosition the job position to create
     * @return the created job position
     */
    @PostMapping
    public JobPosition createJobPosition(@RequestBody JobPosition jobPosition) {
        return jobPositionService.saveJobPosition(jobPosition);
    }

    /**
     * Updates an existing job position.
     *
     * @param id           the ID of the job position to update
     * @param jobPosition  the new job position data
     * @return the updated job position, or a 404 Not Found response if the job position does not exist
     */
    @PutMapping("/{id}")
    public ResponseEntity<JobPosition> updateJobPosition(@PathVariable Long id, @RequestBody JobPosition jobPosition) {
        JobPosition updatedJobPosition = jobPositionService.updateJobPosition(id, jobPosition);
        return updatedJobPosition != null ? ResponseEntity.ok(updatedJobPosition) : ResponseEntity.notFound().build();
    }

    /**
     * Deletes a job position by its ID.
     *
     * @param id the ID of the job position to delete
     * @return a 204 No Content response if the deletion was successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobPosition(@PathVariable Long id) {
        jobPositionService.deleteJobPosition(id);
        return ResponseEntity.noContent().build();
    }
}
