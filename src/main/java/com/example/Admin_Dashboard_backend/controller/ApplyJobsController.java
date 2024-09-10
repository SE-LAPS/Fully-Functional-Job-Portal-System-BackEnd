package com.example.Admin_Dashboard_backend.controller;

import com.example.Admin_Dashboard_backend.model.ApplyJobsModel;
import com.example.Admin_Dashboard_backend.service.ApplyJobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST controller for managing job applications.
 */
@RestController
@RequestMapping("/api/jobs/apply")
@CrossOrigin(origins = "*")
public class ApplyJobsController {

    @Autowired
    private ApplyJobsService applyJobsService;

    /**
     * Handles job application submissions with JSON payload.
     *
     * @param applyJobsModel the job application details.
     * @return the saved application or an error message.
     */
    @PostMapping
    public ResponseEntity<?> applyForJob(@RequestBody ApplyJobsModel applyJobsModel) {
        // Check if any required fields are missing or empty
        if (applyJobsModel.getName() == null || applyJobsModel.getName().isEmpty() ||
                applyJobsModel.getEmail() == null || applyJobsModel.getEmail().isEmpty() ||
                applyJobsModel.getCompanyName() == null || applyJobsModel.getCompanyName().isEmpty() ||
                applyJobsModel.getPositionTitle() == null || applyJobsModel.getPositionTitle().isEmpty()) {
            return new ResponseEntity<>("All fields are required", HttpStatus.BAD_REQUEST);
        }

        // Save the job application
        ApplyJobsModel savedApplication = applyJobsService.applyForJob(applyJobsModel);
        return new ResponseEntity<>(savedApplication, HttpStatus.CREATED);
    }

    /**
     * Retrieves all job applications.
     *
     * @return a list of all job applications.
     */
    @GetMapping("/total-applications")
    public ResponseEntity<List<ApplyJobsModel>> getAllApplications() {
        return new ResponseEntity<>(applyJobsService.getAllApplications(), HttpStatus.OK);
    }

    /**
     * Retrieves a job application by its ID.
     *
     * @param id the ID of the job application.
     * @return the job application, or a not found status if it doesn't exist.
     */
    @GetMapping("/applications/{id}")
    public ResponseEntity<ApplyJobsModel> getApplicationById(@PathVariable Long id) {
        ApplyJobsModel application = applyJobsService.getApplicationById(id);
        if (application != null) {
            return new ResponseEntity<>(application, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Updates a job application by its ID.
     *
     * @param id the ID of the job application.
     * @param updatedApplication the updated job application details.
     * @return the updated job application, or a not found status if it doesn't exist.
     */
    @PutMapping("/applications/{id}")
    public ResponseEntity<ApplyJobsModel> updateApplication(@PathVariable Long id, @RequestBody ApplyJobsModel updatedApplication) {
        ApplyJobsModel existingApplication = applyJobsService.getApplicationById(id);
        if (existingApplication == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        existingApplication.setName(updatedApplication.getName());
        existingApplication.setEmail(updatedApplication.getEmail());
        existingApplication.setCompanyName(updatedApplication.getCompanyName());
        existingApplication.setPositionTitle(updatedApplication.getPositionTitle());

        ApplyJobsModel updatedJobApplication = applyJobsService.updateApplication(existingApplication);
        return new ResponseEntity<>(updatedJobApplication, HttpStatus.OK);
    }

    /**
     * Deletes a job application by its ID.
     *
     * @param id the ID of the job application.
     * @return no content status on success.
     */
    @DeleteMapping("/applications/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        applyJobsService.deleteApplication(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Retrieves the count of applications grouped by job title.
     *
     * @return a map of job titles and their corresponding application counts.
     */
    @GetMapping("/application-count")
    public ResponseEntity<Map<String, Integer>> getApplicationCountByJobTitle() {
        Map<String, Integer> applicationCounts = applyJobsService.getApplicationCountByJobTitle();
        return new ResponseEntity<>(applicationCounts, HttpStatus.OK);
    }
}
