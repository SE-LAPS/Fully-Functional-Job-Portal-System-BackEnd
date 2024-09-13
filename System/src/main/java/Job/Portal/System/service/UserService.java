package Job.Portal.System.service;

import Job.Portal.System.model.User;
import java.util.Optional;

public interface UserService {
    // Method to register a new user
    User registerUser(User user);

    // Method to find a user by their ID
    Optional<User> findById(Long id);

    // Method to find a user by their username
    Optional <User> findByUsername(String username);

    // Method to find a user by their email
    Optional<User> findByEmail(String email);
}
