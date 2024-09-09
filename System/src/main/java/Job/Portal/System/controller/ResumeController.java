package Job.Portal.System.controller;

import Job.Portal.System.model.Resume;
import Job.Portal.System.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/resumes")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @GetMapping("/my")
    public ResponseEntity<List<Resume>> getMyResumes(Principal principal) {
        List<Resume> resumes = resumeService.getResumesByUser(principal.getName());
        return ResponseEntity.ok(resumes);
    }
}

