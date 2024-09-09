package Job.Portal.System.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.time.LocalDate;

@Entity
public class WorkExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private workType type;

    private String jobTitle;

    private String company;

    @Lob
    private String description;

    private String location;

    private LocalDate startDate;

    private LocalDate endDate;

    public enum workType {
        FULL_TIME, PART_TIME, SELF_EMPLOYED, FREELANCE, CONTRACT, INTERNSHIP
    }

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;

    // Getters and setters
}

