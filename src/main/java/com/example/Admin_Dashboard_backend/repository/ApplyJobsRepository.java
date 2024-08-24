package com.example.Admin_Dashboard_backend.repository;

import com.example.Admin_Dashboard_backend.model.ApplyJobsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplyJobsRepository extends JpaRepository<ApplyJobsModel, Long> {

    @Query("SELECT a.positionTitle, COUNT(a) FROM ApplyJobsModel a GROUP BY a.positionTitle")
    List<Object[]> countApplicationsByJobTitle();
}
