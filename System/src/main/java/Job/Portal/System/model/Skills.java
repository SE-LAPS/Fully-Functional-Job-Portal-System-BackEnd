package Job.Portal.System.model;

import jakarta.persistence.*;

@Entity
public class Skills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer years;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;

    // Getters and setters
}

