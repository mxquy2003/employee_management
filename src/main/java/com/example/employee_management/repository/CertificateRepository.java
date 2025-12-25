package com.example.employee_management.repository;

import com.example.employee_management.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);

}
