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
    private WorkRepository workRepository;

    public List<Work> getAllWork() {
        return workRepository.findAll();
    }

    public Optional<Work> getWorkById(Long id) {
        return workRepository.findById(id);
    }

    public Work createWork(Work work) {
        return workRepository.save(work);
    }

    public Work updateWork(Long id, Work workDetails) {
        Work work = workRepository.findById(id).orElseThrow(() -> new RuntimeException("Work not found"));
        work.setTitle(workDetails.getTitle());
        work.setCompany(workDetails.getCompany());
        work.setEmpType(workDetails.getEmpType());
        work.setLocation(workDetails.getLocation());
        work.setStartDate(workDetails.getStartDate());
        work.setEndDate(workDetails.getEndDate());
        work.setDescription(workDetails.getDescription());
        return workRepository.save(work);
    }

    public void deleteWork(Long id) {
        workRepository.deleteById(id);
    }
}
