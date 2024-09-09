package Job.Portal.System.repository;

import Job.Portal.System.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for {@link User} entity.
 * Provides methods to interact with the User data source.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Find a user by their username.
     *
     * @param username the username of the user
     * @return the user with the given username
     */
    User findByUsername(String username);

    /**
     * Find a user by their email address.
     *
     * @param email the email address of the user
     * @return the user with the given email address
     */
    Optional<User> findByEmail(String email);
}
