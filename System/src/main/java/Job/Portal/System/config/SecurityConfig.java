package Job.Portal.System.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration  // This class provides Spring Security configuration for the application
@EnableWebSecurity  // Enables Spring Security web security support
public class SecurityConfig {

    /*
     * Bean for AuthenticationManager
     * This bean is required to handle authentication operations.
     * @param authenticationConfiguration The configuration used to create the AuthenticationManager
     * @return The AuthenticationManager bean
     * @throws Exception if the AuthenticationManager could not be created
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /*
     * Bean for SecurityFilterChain
     * This bean defines the security rules for HTTP requests.
     * @param http The HttpSecurity object used to configure security settings
     * @return The SecurityFilterChain bean
     * @throws Exception if the security configuration could not be applied
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configure HTTP security
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))  // Enable CORS with updated method.
                .csrf(csrf -> csrf.disable())  // Disable CSRF.
                .authorizeHttpRequests(authz -> authz
                        // Allow public access to registration and login endpoints
                        .requestMatchers("/api/users/register", "/api/users/login").permitAll()
                        // Require authentication for all other requests
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));  // Disable session management.

        return http.build();  // Build and return the SecurityFilterChain.
    }

    /*
     * Bean for PasswordEncoder
     * This bean provides a password encoder for securely hashing passwords.
     * @return The PasswordEncoder bean (BCrypt implementation)
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Use BCrypt for password encoding.
    }

    /*
     * Bean for CorsConfigurationSource
     * This bean configures CORS settings for the application.
     * @return The CorsConfigurationSource bean
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));  // Allow requests from the React frontend.
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH"));  // Allow common HTTP methods.
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));  // Allow headers necessary for security.
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);  // Apply CORS settings to all endpoints.
        return source;  // Return the configured CORS source.
    }
}
