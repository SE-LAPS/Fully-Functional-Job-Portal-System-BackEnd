package com.example.admin_job_portal_system.repository;

import com.example.admin_job_portal_system.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {
}
