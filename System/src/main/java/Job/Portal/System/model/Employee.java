package Job.Portal.System.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import lombok.Data;

/**
 * Represents an employee or a company in the job portal system.
 */
@Data
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Unique identifier for the employee

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;  // Reference to the associated user (unique)

    @Column(name = "company_name", nullable = false)
    private String companyName;  // Name of the company

    @Column(columnDefinition = "TEXT")
    private String description;  // Description of the company or employee
}
