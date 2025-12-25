package com.example.employee_management.service;

import com.example.employee_management.entity.Certificate;
import com.example.employee_management.repository.CertificateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateService {

    private final CertificateRepository certificateRepository;

    public List<Certificate> getAll() {
        return certificateRepository.findAll();
    }

    public Certificate create(Certificate certificate) {
        return certificateRepository.save(certificate);
    }

    public Certificate update(Long id, Certificate certificate) {
        Certificate existing = certificateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));

        existing.setName(certificate.getName());
        return certificateRepository.save(existing);
    }

    public void delete(Long id) {
        certificateRepository.deleteById(id);
    }
}
