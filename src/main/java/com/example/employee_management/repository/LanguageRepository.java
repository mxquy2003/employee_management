package com.example.employee_management.repository;

import com.example.employee_management.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    boolean existsByNameAndLevel(String name, String level);
    boolean existsByNameAndLevelAndIdNot(String name, String level, Long id);
}
