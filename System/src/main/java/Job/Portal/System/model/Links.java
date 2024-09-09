package Job.Portal.System.model;

import jakarta.persistence.*;

@Entity
public class Links {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;
    private String url;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;
}
