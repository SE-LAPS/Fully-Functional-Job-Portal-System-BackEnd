package com.example.Admin_Dashboard_backend.repository;

import com.example.Admin_Dashboard_backend.model.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPositionRepository extends JpaRepository<JobPosition, Long> {
}
