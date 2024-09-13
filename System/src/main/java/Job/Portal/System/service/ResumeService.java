package Job.Portal.System.service;

import Job.Portal.System.model.*;
import Job.Portal.System.repository.*;
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
    private SkillsRepository skillsRepository;

    @Autowired
    private DegreeRepository degreeRepository;

    @Autowired
    private LinksRepository linksRepository;

    @Autowired
    private ProjectsRepository projectsRepository;

    @Autowired
    private ReferencesRepository referencesRepository;



    public List<Resume> getResumesByUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return resumeRepository.findByUser(user);
    }

    public Resume addResume(Resume resume, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));;
        resume.setUser(user); // Set the user to the resume
        return resumeRepository.save(resume); // Save the resume in the database
    }

    public List<WorkExperience> getWorkExperiencesByResume(Long resumeId) {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new EntityNotFoundException("Resume not found"));
        return workExperienceRepository.findByResume(resume);
    }

    public List<Skills> getSkillsByResume(Long resumeId) {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new EntityNotFoundException("Resume not found"));
        return skillsRepository.findByResume(resume);
    }

    public List<Degree> getDegreeByResume(Long resumeId) {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new EntityNotFoundException("Resume not found"));
        return degreeRepository.findByResume(resume);
    }

    public List<Links> getLinksByResume(Long resumeId) {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new EntityNotFoundException("Resume not found"));
        return linksRepository.findByResume(resume);
    }

    public List<Projects> getProjectsByResume(Long resumeId) {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new EntityNotFoundException("Resume not found"));
        return projectsRepository.findByResume(resume);
    }

    public List<References> getReferencesByResume(Long resumeId) {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new EntityNotFoundException("Resume not found"));
        return referencesRepository.findByResume(resume);
    }
}
