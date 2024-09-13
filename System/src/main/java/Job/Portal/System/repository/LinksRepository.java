package Job.Portal.System.repository;

import Job.Portal.System.model.Resume;
import Job.Portal.System.model.Links;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinksRepository extends JpaRepository<Links, Long> {
    List<Links> findByResume(Resume resume);
}


