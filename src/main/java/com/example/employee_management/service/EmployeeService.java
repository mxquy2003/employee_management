package com.example.employee_management.service;

import com.example.employee_management.dto.request.EmployeeRequest;
import com.example.employee_management.dto.response.EmployeeResponse;
import com.example.employee_management.entity.Certificate;
import com.example.employee_management.entity.Employee;
import com.example.employee_management.entity.Language;
import com.example.employee_management.repository.CertificateRepository;
import com.example.employee_management.repository.EmployeeRepository;
import com.example.employee_management.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final LanguageRepository languageRepository;
    private final CertificateRepository certificateRepository;

    @Transactional(readOnly = true)
    public List<EmployeeResponse> getAll() {
        return employeeRepository.findAll()
                .stream()
                .map(e -> new EmployeeResponse(
                        e.getId(),
                        e.getName(),
                        e.getDob(),
                        e.getAddress(),
                        e.getPhone(),
                        e.getLanguages(),
                        e.getCertificates()
                ))
                .toList();
    }

    public void create(EmployeeRequest request) {
        Employee e = new Employee();
        mapRequestToEmployee(e, request);
        employeeRepository.save(e);
    }

    public void update(Long id, EmployeeRequest request) {
        Employee e = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        mapRequestToEmployee(e, request);
        employeeRepository.save(e);
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    private void mapRequestToEmployee(Employee e, EmployeeRequest request) {
        e.setName(request.getName());
        e.setDob(request.getDob());
        e.setAddress(request.getAddress());
        e.setPhone(request.getPhone());

        if (request.getLanguageIds() != null) {
            List<Language> languages = languageRepository.findAllById(request.getLanguageIds());
            e.setLanguages(new HashSet<>(languages));
        }

        if (request.getCertificateIds() != null) {
            List<Certificate> certificates = certificateRepository.findAllById(request.getCertificateIds());
            e.setCertificates(new HashSet<>(certificates));
        }
    }
}