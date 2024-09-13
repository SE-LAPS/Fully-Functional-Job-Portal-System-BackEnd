package Job.Portal.System.repository;

import Job.Portal.System.model.Skills;
import Job.Portal.System.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillsRepository extends JpaRepository<Skills, Long> {
    List<Skills> findByResume(Resume resume);
}

