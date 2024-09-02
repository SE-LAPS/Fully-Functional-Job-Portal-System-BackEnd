package com.example.Admin_Dashboard_backend.repository;

import com.example.Admin_Dashboard_backend.model.ApplyJobsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing job applications.
 */
@Repository
public interface ApplyJobsRepository extends JpaRepository<ApplyJobsModel, Long> {

    /**
     * Counts the number of applications grouped by job title.
     *
     * @return a list of job titles and their corresponding application counts.
     */
    @Query("SELECT aj.positionTitle, COUNT(aj) FROM ApplyJobsModel aj GROUP BY aj.positionTitle")
    List<Object[]> countApplicationsByJobTitle();
}
