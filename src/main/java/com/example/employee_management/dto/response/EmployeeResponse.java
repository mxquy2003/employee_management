package com.example.employee_management.dto.response;

import com.example.employee_management.entity.Certificate;
import com.example.employee_management.entity.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeResponse {
    private Long id;
    private String name;
    private LocalDate dob;
    private String address;
    private String phone;
    private Set<Language> languages;
    private Set<Certificate> certificates;
}