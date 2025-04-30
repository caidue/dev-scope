package com.caidu.devscope.control;

import com.caidu.devscope.dto.UserDTO;
import com.caidu.devscope.entity.User;
import com.caidu.devscope.repository.UserRepository;
import io.quarkus.oidc.UserInfo;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.eclipse.microprofile.jwt.JsonWebToken;

@ApplicationScoped
public class AuthService {

    @Inject SecurityIdentity securityIdentity;

    @Inject JsonWebToken jwt;

    @Inject UserInfo userInfo;

    @Inject UserRepository userRepository;

    @Inject UserService userService;

    /** Get the current authenticated user from the database */
    public Optional<UserDTO> getCurrentUser() {
        if (!securityIdentity.isAnonymous() && jwt != null) {
            String keycloakId = jwt.getSubject();
            return userRepository
                    .find("keycloakId", keycloakId)
                    .firstResultOptional()
                    .map(
                            user ->
                                    new UserDTO(
                                            user.id,
                                            user.getUsername(),
                                            user.getEmail(),
                                            user.getKeycloakId()));
        }
        return Optional.empty();
    }

    /** Synchronize user from Keycloak to local database */
    @Transactional
    public UserDTO syncUserFromKeycloak() {
        if (securityIdentity.isAnonymous() || jwt == null) {
            throw new RuntimeException("User not authenticated or OIDC not available");
        }

        String keycloakId = jwt.getSubject();
        String username = jwt.getClaim("preferred_username");
        String email = jwt.getClaim("email");

        // Check if user already exists
        Optional<User> existingUser =
                userRepository.find("keycloakId", keycloakId).firstResultOptional();

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            // Update user info if needed
            if (!username.equals(user.getUsername()) || !email.equals(user.getEmail())) {
                user.setUsername(username);
                user.setEmail(email);
                userRepository.persist(user);
            }
            return new UserDTO(user.id, user.getUsername(), user.getEmail(), user.getKeycloakId());
        } else {
            // Create new user
            UserDTO newUser = new UserDTO(null, username, email, keycloakId);
            return userService.create(newUser);
        }
    }

    /** Check if current user has a specific role */
    public boolean hasRole(String role) {
        return securityIdentity.hasRole(role);
    }

    /** Get user info from JWT token */
    public String getUsername() {
        return jwt != null ? jwt.getClaim("preferred_username") : null;
    }

    public String getEmail() {
        return jwt != null ? jwt.getClaim("email") : null;
    }

    public String getKeycloakId() {
        return jwt != null ? jwt.getSubject() : null;
    }
}
