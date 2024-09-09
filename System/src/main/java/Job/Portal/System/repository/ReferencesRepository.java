package Job.Portal.System.repository;

import Job.Portal.System.model.Resume;
import Job.Portal.System.model.References;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReferencesRepository extends JpaRepository<References, Long> {
    List<References> findByResume(Resume resume);
}




