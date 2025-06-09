package com.onus.crud_review1.repositories;

import com.onus.crud_review1.entities.Users;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, String> {
    Users findByUserName(String userName);
    boolean existsByEmail(@NotEmpty(message = "email should not be empty") String email);
}
