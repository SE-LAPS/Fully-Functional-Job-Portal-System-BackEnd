package Job.Portal.System.controller;

import Job.Portal.System.model.Job;
import Job.Portal.System.model.Employee;
import Job.Portal.System.model.JobCategory;
import Job.Portal.System.model.User;
import Job.Portal.System.service.JobService;
import Job.Portal.System.service.EmployeeService;
import Job.Portal.System.service.JobCategoryService;
import Job.Portal.System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    // Injecting JobService dependency
    @Autowired
    private JobService jobService;

    // Injecting EmployeeService dependency
    @Autowired
    private EmployeeService employeeService;

    // Injecting JobCategoryService dependency
    @Autowired
    private JobCategoryService jobCategoryService;

    // Injecting UserService dependency
    @Autowired
    private UserService userService;

    /*
     * Add a new job
     * This method allows adding a new job to the system.
     */
    @PostMapping
    public ResponseEntity<Job> addJob(@RequestBody Job job) {
        Job newJob = jobService.addJob(job);  // Add the new job
        return ResponseEntity.ok(newJob);  // Return the newly added job
    }

    /*
     * Get jobs by company
     * This method retrieves all jobs associated with a particular company.
     */
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<Job>> getJobsByCompany(@PathVariable Long companyId) {
        // Convert companyId to username if necessary
        String username = companyId.toString();

        // Fetch user by username
        Optional<User> userOptional = userService.findByUsername(username);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build(); // Return 404 if user is not found
        }

        User user = userOptional.get();
        Employee company = employeeService.findByUser(user);
        if (company != null) {
            List<Job> jobs = jobService.getJobsByCompany(company);
            return ResponseEntity.ok(jobs);
        }

        return ResponseEntity.notFound().build(); // Return 404 if company is not found
    }

    /*
     * Get all jobs
     * This method retrieves all jobs available in the system.
     */
    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> jobs = jobService.getAllJobs();  // Get all jobs
        return ResponseEntity.ok(jobs);  // Return the list of jobs
    }

    /*
     * Search jobs by keyword
     * This method searches for jobs based on a keyword.
     */
    @GetMapping("/search")
    public ResponseEntity<List<Job>> searchJobs(@RequestParam String keyword) {
        List<Job> jobs = jobService.searchJobs(keyword);  // Search for jobs using the keyword
        return ResponseEntity.ok(jobs);  // Return the list of matching jobs
    }

    /*
     * Get jobs by category
     * This method retrieves all jobs within a specific category.
     */
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Job>> getJobsByCategory(@PathVariable Long categoryId) {
        JobCategory category = jobCategoryService.findById(categoryId);  // Find the job category
        if (category != null) {
            List<Job> jobs = jobService.getJobsByCategory(category);  // Get jobs for the category
            return ResponseEntity.ok(jobs);  // Return the list of jobs
        }
        return ResponseEntity.notFound().build();  // Return 404 if category is not found
    }
}
