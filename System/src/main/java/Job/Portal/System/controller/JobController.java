package Job.Portal.System.controller;

import Job.Portal.System.consumer.KafkaConsumer;
import Job.Portal.System.model.Job;
import Job.Portal.System.model.Employee;
import Job.Portal.System.model.JobCategory;

import Job.Portal.System.payload.AuthPayLoad;
import Job.Portal.System.payload.AuthResult;
import Job.Portal.System.producer.KafkaJsonProducer;
import Job.Portal.System.service.JobService;
import Job.Portal.System.service.EmployeeService;
import Job.Portal.System.service.JobCategoryService;
import Job.Portal.System.service.UserService;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.UUID;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
@Slf4j
public class JobController {

    private final KafkaJsonProducer kafkaJsonProducer;
    private final KafkaConsumer kafkaConsumer;
    private AuthResult authResult;
    UUID uniqueId = UUID.randomUUID();
    AuthPayLoad authPayLoad = new AuthPayLoad();

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

    boolean authorizedToken(String token){
        authPayLoad.setId(uniqueId);
        authPayLoad.setToken(token);
        kafkaJsonProducer.sendJsonMessage(authPayLoad);
        authResult = kafkaConsumer.consumeJsonMsg();
        return
    }

    /*
     * Add a new job
     * This method allows adding a new job to the system.
     */
    @PostMapping
    public ResponseEntity<Job> addJob(@RequestHeader("Authorization") String token, @RequestBody Job job) {
        if(authorizedToken(token)){
            Job newJob = jobService.addJob(job);  // Add the new job
            return ResponseEntity.ok(newJob);  // Return the newly added job
        }else{
           return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    /*
     * Get jobs by company
     * This method retrieves all jobs associated with a particular company.
     */
    @GetMapping("/company/{companyId}")

    public ResponseEntity<List<Job>> getJobsByCompany(@PathVariable Long companyId, @RequestHeader("Authorization") String token) {
        if(authorizedToken(token)){
            Employee company = employeeService.findByUser(userService.findByUsername(companyId.toString()));  // Find the company
            if (company != null) {
                List<Job> jobs = jobService.getJobsByCompany(company);  // Get jobs for the company
                return ResponseEntity.ok(jobs);  // Return the list of jobs
            }
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }// Return 404 if company is not found
    }

    /*
     * Get all jobs
     * This method retrieves all jobs available in the system.
     */
    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs(@RequestHeader("Authorization") String token) {
        if(authorizedToken(token)){
            List<Job> jobs = jobService.getAllJobs();  // Get all jobs
            return ResponseEntity.ok(jobs);  // Return the list of jobs
        }else{
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    /*
     * Search jobs by keyword
     * This method searches for jobs based on a keyword.
     */
    @GetMapping("/search")
    public ResponseEntity<List<Job>> searchJobs(@RequestParam String keyword,@RequestHeader("Authorization") String token) {
        if(authorizedToken(token)){
            List<Job> jobs = jobService.searchJobs(keyword);  // Search for jobs using the keyword
            return ResponseEntity.ok(jobs);  // Return the list of matching jobs
        }else{
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    /*
     * Get jobs by category
     * This method retrieves all jobs within a specific category.
     */
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Job>> getJobsByCategory(@PathVariable Long categoryId,@RequestHeader("Authorization") String token) {
        if(authorizedToken(token)){
            JobCategory category = jobCategoryService.findById(categoryId);  // Find the job category
            if (category != null) {
                List<Job> jobs = jobService.getJobsByCategory(category);  // Get jobs for the category
                return ResponseEntity.ok(jobs);  // Return the list of jobs
            }
            return ResponseEntity.notFound().build();  // Return 404 if category is not found
        }else{
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }
}
