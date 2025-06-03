package com.onus.crud_review1.repositories;

import com.onus.crud_review1.entities.Employees;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employees, String> {
    boolean existsByEmail(String email);
    Employees findByEmail(String email);
    Page<Employees> findAllByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String firstName,
            String lastName,
            String email,
            Pageable pageable
    );
}
