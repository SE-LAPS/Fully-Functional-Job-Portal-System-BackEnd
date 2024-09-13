package Job.Portal.System.model;

import jakarta.persistence.*;

@Entity
public class References {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String organization;
    private String phone;
    private String email;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;
}
