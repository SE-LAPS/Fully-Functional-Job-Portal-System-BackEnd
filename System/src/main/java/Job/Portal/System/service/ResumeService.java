package Job.Portal.System.service;

import Job.Portal.System.model.Resume;
import Job.Portal.System.model.User;
import Job.Portal.System.model.WorkExperience;
import Job.Portal.System.model.Skills;
import Job.Portal.System.repository.ResumeRepository;
import Job.Portal.System.repository.SkillsRepository;
import Job.Portal.System.repository.UserRepository;
import Job.Portal.System.repository.WorkExperienceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

@Service
public class ResumeService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private WorkExperienceRepository workExperienceRepository;

    @Autowired
    private SkillsRepository skillRepository;

    public List<Resume> getResumesByUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return resumeRepository.findByUser(user);
    }

    public List<WorkExperience> getWorkExperiencesByResume(Long resumeId) {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new EntityNotFoundException("Resume not found"));
        return workExperienceRepository.findByResume(resume);
    }

    public List<Skills> getSkillsByResume(Long resumeId) {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new EntityNotFoundException("Resume not found"));
        return skillRepository.findByResume(resume);
    }
}


