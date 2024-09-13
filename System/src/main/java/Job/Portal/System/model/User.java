package Job.Portal.System.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * Represents a user in the job portal system.
 */
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Unique identifier for the user

    @Column(unique = true, nullable = false)
    private String username;  // Username of the user

    @Column(nullable = false)
    private String password;  // Password of the user

    @Column(unique = true, nullable = false)
    private String email;  // Email address of the user

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;  // Role of the user (e.g., ADMIN, JOB_SEEKER, COMPANY)

    /**
     * Enum representing the role of a user in the system.
     */
    public enum Role {
        ADMIN, JOB_SEEKER, COMPANY
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Resume> resumes;
}
