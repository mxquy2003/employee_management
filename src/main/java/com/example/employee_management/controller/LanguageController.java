package com.example.employee_management.controller;

import com.example.employee_management.entity.Language;
import com.example.employee_management.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageService languageService;

    @GetMapping
    public ResponseEntity<List<Language>> getAll() {
        return ResponseEntity.ok(languageService.getAll());
    }

    @PostMapping
    public ResponseEntity<Language> create(@RequestBody Language language) {
        return ResponseEntity.ok(languageService.create(language));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Language> update(
            @PathVariable Long id,
            @RequestBody Language language) {
        return ResponseEntity.ok(languageService.update(id, language));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        languageService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
