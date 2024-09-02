package com.example.Admin_Dashboard_backend.repository;

import com.example.Admin_Dashboard_backend.model.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing job positions in the database.
 * Extends JpaRepository to provide CRUD operations.
 */
public interface JobPositionRepository extends JpaRepository<JobPosition, Long> {
}
