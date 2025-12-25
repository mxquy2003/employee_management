package com.example.employee_management.controller;

import com.example.employee_management.entity.Certificate;
import com.example.employee_management.service.CertificateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/certificates")
@RequiredArgsConstructor
public class CertificateController {

    private final CertificateService certificateService;

    @GetMapping
    public List<Certificate> getAll() {
        return certificateService.getAll();
    }

    @PostMapping
    public Certificate create(@Valid @RequestBody Certificate certificate) {
        return certificateService.create(certificate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Certificate> update(@PathVariable Long id, @Valid @RequestBody Certificate certificate) {
        return ResponseEntity.ok(certificateService.update(id, certificate));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        certificateService.delete(id);
    }
}