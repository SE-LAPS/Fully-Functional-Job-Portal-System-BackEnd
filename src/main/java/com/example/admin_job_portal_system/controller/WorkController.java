package com.example.admin_job_portal_system.controller;

import com.example.admin_job_portal_system.entity.Work;
import com.example.admin_job_portal_system.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/work")
public class WorkController {

    @Autowired
    private WorkService workService; // Injecting the WorkService dependency

    @GetMapping
    public List<Work> getAllWork() {
        // Retrieve and return a list of all Work entities
        return workService.getAllWork();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Work> getWorkById(@PathVariable Long id) {
        // Retrieve a Work entity by its ID and return it wrapped in a ResponseEntity
        return workService.getWorkById(id)
                .map(ResponseEntity::ok) // If the Work entity is found, return it with HTTP status 200
                .orElseGet(() -> ResponseEntity.notFound().build()); // If not found, return HTTP status 404
    }

    @PostMapping
    public Work createWork(@RequestBody Work work) {
        // Create a new Work entity and return the created entity
        return workService.createWork(work);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Work> updateWork(@PathVariable Long id, @RequestBody Work workDetails) {
        // Update the Work entity with the given ID using the provided details and return the updated entity
        return ResponseEntity.ok(workService.updateWork(id, workDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWork(@PathVariable Long id) {
        // Delete the Work entity with the given ID
        workService.deleteWork(id);
        return ResponseEntity.noContent().build(); // Return HTTP status 204 (No Content)
    }
}
