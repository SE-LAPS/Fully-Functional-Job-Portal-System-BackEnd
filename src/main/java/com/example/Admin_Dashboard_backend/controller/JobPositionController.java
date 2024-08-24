package com.example.Admin_Dashboard_backend.controller;

import com.example.Admin_Dashboard_backend.model.JobPosition;
import com.example.Admin_Dashboard_backend.service.JobPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")
public class JobPositionController {

    private final JobPositionService jobPositionService;

    @Autowired
    public JobPositionController(JobPositionService jobPositionService) {
        this.jobPositionService = jobPositionService;
    }

    @GetMapping
    public List<JobPosition> getAllJobPositions() {
        return jobPositionService.getAllJobPositions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobPosition> getJobPositionById(@PathVariable Long id) {
        Optional<JobPosition> jobPosition = jobPositionService.getJobPositionById(id);
        return jobPosition.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public JobPosition createJobPosition(@RequestBody JobPosition jobPosition) {
        return jobPositionService.saveJobPosition(jobPosition);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobPosition> updateJobPosition(@PathVariable Long id, @RequestBody JobPosition jobPosition) {
        JobPosition updatedJobPosition = jobPositionService.updateJobPosition(id, jobPosition);
        return updatedJobPosition != null ? ResponseEntity.ok(updatedJobPosition) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobPosition(@PathVariable Long id) {
        jobPositionService.deleteJobPosition(id);
        return ResponseEntity.noContent().build();
    }
}
