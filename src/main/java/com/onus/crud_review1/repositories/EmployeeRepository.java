package com.onus.crud_review1.repositories;

import com.onus.crud_review1.entities.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employees, String> {
    boolean existsByEmail(String email);
    Employees findByEmail(String email);
}
