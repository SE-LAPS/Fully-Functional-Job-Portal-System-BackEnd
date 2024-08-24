package com.example.Admin_Dashboard_backend.service;

import com.example.Admin_Dashboard_backend.model.ApplyJobsModel;
import com.example.Admin_Dashboard_backend.repository.ApplyJobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApplyJobsServiceImpl implements ApplyJobsService {

    @Autowired
    private ApplyJobsRepository applyJobsRepository;

    @Override
    public ApplyJobsModel applyForJob(ApplyJobsModel applyJobsModel) {
        return applyJobsRepository.save(applyJobsModel);
    }

    @Override
    public List<ApplyJobsModel> getAllApplications() {
        return applyJobsRepository.findAll();
    }

    @Override
    public ApplyJobsModel getApplicationById(Long id) {
        return applyJobsRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteApplication(Long id) {
        applyJobsRepository.deleteById(id);
    }

    @Override
    public Map<String, Integer> getApplicationCountByJobTitle() {
        List<Object[]> results = applyJobsRepository.countApplicationsByJobTitle();
        Map<String, Integer> applicationCounts = new HashMap<>();
        for (Object[] result : results) {
            String jobTitle = (String) result[0];
            Integer count = ((Number) result[1]).intValue();
            applicationCounts.put(jobTitle, count);
        }
        return applicationCounts;
    }
}
