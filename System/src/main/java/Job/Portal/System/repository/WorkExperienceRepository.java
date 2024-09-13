package Job.Portal.System.repository;

import Job.Portal.System.model.Resume;
import Job.Portal.System.model.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Long> {
    List<WorkExperience> findByResume(Resume resume);
}
