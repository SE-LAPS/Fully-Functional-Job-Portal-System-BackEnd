package com.example.admin_job_portal_system.service;

import com.example.admin_job_portal_system.entity.Work;
import com.example.admin_job_portal_system.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkService {

    @Autowired
    private WorkRepository workRepository; // Inject WorkRepository

    public List<Work> getAllWork() {
        // Retrieve list of all Work records from db
        return workRepository.findAll();
    }

    public Optional<Work> getWorkById(Long id) {
        // Retrieve Work records by its ID
        return workRepository.findById(id);
    }

    public Work createWork(Work work) {
        // Save a new Work entity to db
        return workRepository.save(work);
    }

    public Work updateWork(Long id, Work workDetails) {
        // Retrieve the existing work by ID and exception
        Work work = workRepository.findById(id).orElseThrow(() -> new RuntimeException("Work not found"));

        // Update the Work entity's fields with the provided details
        work.setTitle(workDetails.getTitle());
        work.setCompany(workDetails.getCompany());
        work.setEmpType(workDetails.getEmpType());
        work.setLocation(workDetails.getLocation());
        work.setStartDate(workDetails.getStartDate());
        work.setEndDate(workDetails.getEndDate());
        work.setDescription(workDetails.getDescription());

        // Save the updated Work entity to the database and return it
        return workRepository.save(work);
    }

    public void deleteWork(Long id) {
        // Delete the Work entity with the given ID from the database
        workRepository.deleteById(id);
    }
}
