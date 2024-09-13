package Job.Portal.System.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String degree;

    private String school;

    private LocalDate gradDate;

    private String grade;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;
}
