package com.example.employee_management.controller;

import com.example.employee_management.entity.Certificate;
import com.example.employee_management.repository.CertificateRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/certificates")
public class CertificateController {

    @Autowired
    private CertificateRepository certificateRepository;

    @GetMapping
    public List<Certificate> getAll() {
        return certificateRepository.findAll();
    }

    @PostMapping
    public Certificate create(@Valid @RequestBody Certificate certificate) {
        return certificateRepository.save(certificate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Certificate> update(@PathVariable Long id, @Valid @RequestBody Certificate details) {
        return certificateRepository.findById(id)
                .map(cert -> {
                    cert.setName(details.getName());
                    return ResponseEntity.ok(certificateRepository.save(cert));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        certificateRepository.deleteById(id);
    }
}