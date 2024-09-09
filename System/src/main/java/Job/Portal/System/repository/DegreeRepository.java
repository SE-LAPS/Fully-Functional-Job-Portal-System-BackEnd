package Job.Portal.System.repository;

import Job.Portal.System.model.Resume;
import Job.Portal.System.model.Degree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DegreeRepository extends JpaRepository<Degree, Long> {
    List<Degree> findByResume(Resume resume);
}

