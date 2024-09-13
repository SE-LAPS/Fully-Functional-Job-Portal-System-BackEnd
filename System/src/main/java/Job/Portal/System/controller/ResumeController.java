package Job.Portal.System.controller;

import Job.Portal.System.model.*;
import Job.Portal.System.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/resumes")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping
    public ResponseEntity<Resume> addResume(@RequestBody Resume resume, Principal principal) {
        Resume createdResume = resumeService.addResume(resume, principal.getName());
        return ResponseEntity.ok(createdResume);
    }

    @GetMapping("/my")
    public ResponseEntity<List<Resume>> getMyResumes(Principal principal) {
        List<Resume> resumes = resumeService.getResumesByUser(principal.getName());
        return ResponseEntity.ok(resumes);
    }

    @GetMapping("/{resumeId}/work-experiences")
    public ResponseEntity<List<WorkExperience>> getWorkExperiences(@PathVariable Long resumeId) {
        List<WorkExperience> workExperiences = resumeService.getWorkExperiencesByResume(resumeId);
        return ResponseEntity.ok(workExperiences);
    }

    @GetMapping("/{resumeId}/skills")
    public ResponseEntity<List<Skills>> getSkills(@PathVariable Long resumeId) {
        List<Skills> skills = resumeService.getSkillsByResume(resumeId);
        return ResponseEntity.ok(skills);
    }

    @GetMapping("/{resumeId}/degree")
    public ResponseEntity<List<Degree>> getDegree(@PathVariable Long resumeId) {
        List<Degree> degree = resumeService.getDegreeByResume(resumeId);
        return ResponseEntity.ok(degree);
    }

    @GetMapping("/{resumeId}/links")
    public ResponseEntity<List<Links>> getLinks(@PathVariable Long resumeId) {
        List<Links> links = resumeService.getLinksByResume(resumeId);
        return ResponseEntity.ok(links);
    }

    @GetMapping("/{resumeId}/projects")
    public ResponseEntity<List<Projects>> getProjects(@PathVariable Long resumeId) {
        List<Projects> projects = resumeService.getProjectsByResume(resumeId);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{resumeId}/references")
    public ResponseEntity<List<References>> getReferences(@PathVariable Long resumeId) {
        List<References> references = resumeService.getReferencesByResume(resumeId);
        return ResponseEntity.ok(references);
    }
}

