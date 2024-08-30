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

@RestController
@RequestMapping("/api/jobs/apply")
@CrossOrigin(origins = "*")  // Allow all origins for CORS
public class ApplyJobsController {

    @Autowired
    private ApplyJobsService applyJobsService;

    @PostMapping("/apply")
    public ResponseEntity<?> applyForJob(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("companyName") String companyName,
            @RequestParam("positionTitle") String positionTitle,
            @RequestParam("resume") MultipartFile resume) {

        // Validate input
        if (name.isEmpty() || email.isEmpty() || companyName.isEmpty() || positionTitle.isEmpty() || resume.isEmpty()) {
            return new ResponseEntity<>("All fields are required", HttpStatus.BAD_REQUEST);
        }

        // Save the resume file
        String resumeFilePath = saveResumeFile(resume);
        if (resumeFilePath == null) {
            return new ResponseEntity<>("Failed to save resume file", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Creating the ApplyJobsModel object
        ApplyJobsModel applyJobsModel = new ApplyJobsModel(name, email, resumeFilePath, companyName, positionTitle);

        // Saving the job application
        ApplyJobsModel savedApplication = applyJobsService.applyForJob(applyJobsModel);
        return new ResponseEntity<>(savedApplication, HttpStatus.CREATED);
    }

    @GetMapping("/total-applications")
    public ResponseEntity<List<ApplyJobsModel>> getAllApplications() {
        return new ResponseEntity<>(applyJobsService.getAllApplications(), HttpStatus.OK);
    }

    @GetMapping("/applications/{id}")
    public ResponseEntity<ApplyJobsModel> getApplicationById(@PathVariable Long id) {
        ApplyJobsModel application = applyJobsService.getApplicationById(id);
        if (application != null) {
            return new ResponseEntity<>(application, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/applications/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        applyJobsService.deleteApplication(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/application-count")
    public ResponseEntity<Map<String, Integer>> getApplicationCountByJobTitle() {
        Map<String, Integer> applicationCounts = applyJobsService.getApplicationCountByJobTitle();
        return new ResponseEntity<>(applicationCounts, HttpStatus.OK);
    }

    private String saveResumeFile(MultipartFile file) {
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        String filePath = "uploads/" + fileName;
        File dest = new File(filePath);

        try {
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();  // Create directories if they don't exist
            }
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return filePath;
    }
}
