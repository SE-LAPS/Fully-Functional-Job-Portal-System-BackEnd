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
    private WorkService workService;

    @GetMapping
    public List<Work> getAllWork() {
        return workService.getAllWork();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Work> getWorkById(@PathVariable Long id) {
        return workService.getWorkById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Work createWork(@RequestBody Work work) {
        return workService.createWork(work);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Work> updateWork(@PathVariable Long id, @RequestBody Work workDetails) {
        return ResponseEntity.ok(workService.updateWork(id, workDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWork(@PathVariable Long id) {
        workService.deleteWork(id);
        return ResponseEntity.noContent().build();
    }
}
