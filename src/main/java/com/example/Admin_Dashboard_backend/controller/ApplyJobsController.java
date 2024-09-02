package com.example.Admin_Dashboard_backend.controller;

import com.example.Admin_Dashboard_backend.model.ApplyJobsModel;
import com.example.Admin_Dashboard_backend.service.ApplyJobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
     * Handles job application submissions.
     *
     * @param name          the name of the applicant.
     * @param email         the email of the applicant.
     * @param companyName   the company name.
     * @param positionTitle the job title.
     * @param resume        the resume file.
     * @return the saved application or an error message.
     */
    @PostMapping("/apply")
    public ResponseEntity<?> applyForJob(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("companyName") String companyName,
            @RequestParam("positionTitle") String positionTitle,
            @RequestParam("resume") MultipartFile resume) {

        if (name.isEmpty() || email.isEmpty() || companyName.isEmpty() || positionTitle.isEmpty() || resume.isEmpty()) {
            return new ResponseEntity<>("All fields are required", HttpStatus.BAD_REQUEST);
        }

        String resumeFilePath = saveResumeFile(resume);
        if (resumeFilePath == null) {
            return new ResponseEntity<>("Failed to save resume file", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        ApplyJobsModel applyJobsModel = new ApplyJobsModel(name, email, resumeFilePath, companyName, positionTitle);
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
     * @param id                the ID of the job application.
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

    /**
     * Saves the resume file to the server.
     *
     * @param file the resume file to save.
     * @return the file path where the resume was saved, or null if an error occurred.
     */
    private String saveResumeFile(MultipartFile file) {
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        String filePath = "uploads/" + fileName;
        File dest = new File(filePath);

        try {
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return filePath;
    }
}
