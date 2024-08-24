package com.example.Admin_Dashboard_backend.service;

import com.example.Admin_Dashboard_backend.model.ApplyJobsModel;

import java.util.List;
import java.util.Map;

public interface ApplyJobsService {
    ApplyJobsModel applyForJob(ApplyJobsModel applyJobsModel);
    List<ApplyJobsModel> getAllApplications();
    ApplyJobsModel getApplicationById(Long id);
    void deleteApplication(Long id);
    Map<String, Integer> getApplicationCountByJobTitle();
}
