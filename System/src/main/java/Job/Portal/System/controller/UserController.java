package Job.Portal.System.controller;

import Job.Portal.System.model.User;
import Job.Portal.System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController  // This class handles HTTP requests for user-related operations
@RequestMapping("/api/users")  // Base URL for all user-related endpoints
public class UserController {

    // Injecting UserService dependency
    @Autowired
    private UserService userService;

    // Injecting AuthenticationManager dependency for handling authentication
    @Autowired
    private AuthenticationManager authenticationManager;

    /*
     * Register a new user
     * This method registers a new user in the system.
     * @param user The user details provided in the request body
     * @return A ResponseEntity containing the registered user or an error message
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            // Attempt to register the user
            User registeredUser = userService.registerUser(user);
            return ResponseEntity.ok(registeredUser);  // Return the registered user
        } catch (Exception e) {
            // Handle registration failure and return an appropriate response
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
        }
    }

    /*
     * Login a user
     * This method authenticates a user and returns a success message along with user details.
     * @param loginUser The user's login credentials provided in the request body
     * @return A ResponseEntity containing a success message, user details, or an error message
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginUser) {
        try {
            // Attempt to authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword())
            );

            // Set the authentication context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Retrieve the authenticated user's details
            User user = userService.findByUsername(loginUser.getUsername());

            // Prepare the response payload
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("user", user);
            // Note: You might want to generate and include a JWT token here

            return ResponseEntity.ok(response);  // Return the successful login response
        } catch (Exception e) {
            // Handle login failure and return an appropriate response
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed: " + e.getMessage());
        }
    }

    /*
     * Get user by username
     * This method retrieves a user based on the provided username.
     * @param username The username provided in the path variable
     * @return A ResponseEntity containing the user details or a 404 status if not found
     */
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username);  // Find the user by username
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();  // Return the user or 404 if not found
    }

    /*
     * Get user by email
     * This method retrieves a user based on the provided email.
     * @param email The email provided in the path variable
     * @return A ResponseEntity containing the user details or a 404 status if not found
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = userService.findByEmail(email);  // Find the user by email
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();  // Return the user or 404 if not found
    }
}
