package Job.Portal.System.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String description;

    private Integer duration;

    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;


}
