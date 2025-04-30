package com.caidu.devscope.repository;

import com.caidu.devscope.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    // Custom queries can be added here
    // Example:
    // public Optional<User> findByEmail(String email) {
    // return find("email", email).firstResultOptional();
    // }
}
