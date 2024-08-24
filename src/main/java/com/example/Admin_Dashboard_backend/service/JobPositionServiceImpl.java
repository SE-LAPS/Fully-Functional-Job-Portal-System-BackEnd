package com.example.Admin_Dashboard_backend.service;

import com.example.Admin_Dashboard_backend.model.JobPosition;
import com.example.Admin_Dashboard_backend.repository.JobPositionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobPositionServiceImpl implements JobPositionService {
    private final JobPositionRepository jobPositionRepository;

    public JobPositionServiceImpl(JobPositionRepository jobPositionRepository) {
        this.jobPositionRepository = jobPositionRepository;
    }

    @Override
    public List<JobPosition> getAllJobPositions() {
        return jobPositionRepository.findAll();
    }

    @Override
    public Optional<JobPosition> getJobPositionById(Integer id) {
        return Optional.empty();
    }

    @Override
    public JobPosition createJobPosition(JobPosition jobPosition) {
        return null;
    }

    @Override
    public JobPosition updateJobPosition(JobPosition jobPosition) {
        return null;
    }

    @Override
    public boolean deleteJobPosition(Integer id) {
        return false;
    }

    @Override
    public JobPosition saveJobPosition(JobPosition jobPosition) {
        return jobPositionRepository.save(jobPosition);
    }

    @Override
    public Optional<JobPosition> getJobPositionById(Long id) {
        return jobPositionRepository.findById(id);
    }

    @Override
    public JobPosition updateJobPosition(Long id, JobPosition jobPosition) {
        Optional<JobPosition> existingJobPosition = jobPositionRepository.findById(id);
        if (existingJobPosition.isPresent()) {
            JobPosition updatedJobPosition = existingJobPosition.get();
            updatedJobPosition.setPositionTitle(jobPosition.getPositionTitle());
            updatedJobPosition.setCategory(jobPosition.getCategory());
            updatedJobPosition.setSalaryRange(jobPosition.getSalaryRange());
            updatedJobPosition.setJobType(jobPosition.getJobType());
            updatedJobPosition.setApplicationLinkOrEmail(jobPosition.getApplicationLinkOrEmail());
            updatedJobPosition.setPositionDescription(jobPosition.getPositionDescription());
            updatedJobPosition.setCompanyName(jobPosition.getCompanyName());
            updatedJobPosition.setCompanyWebsite(jobPosition.getCompanyWebsite());
            updatedJobPosition.setEmailAddress(jobPosition.getEmailAddress());
            updatedJobPosition.setCompanyDescription(jobPosition.getCompanyDescription());
            updatedJobPosition.setLocation(jobPosition.getLocation());
            return jobPositionRepository.save(updatedJobPosition);
        }
        return null; // Handle not found scenario appropriately
    }

    @Override
    public void deleteJobPosition(Long id) {
        jobPositionRepository.deleteById(id);
    }
}
