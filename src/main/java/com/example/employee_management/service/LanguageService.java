package com.example.employee_management.service;

import com.example.employee_management.entity.Language;
import com.example.employee_management.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageService {

    private final LanguageRepository languageRepository;

    public List<Language> getAll() {
        return languageRepository.findAll();
    }

    public Language create(Language language) {
        return languageRepository.save(language);
    }

    public Language update(Long id, Language language) {
        Language existing = languageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Language not found"));

        existing.setName(language.getName());
        existing.setLevel(language.getLevel());

        return languageRepository.save(existing);
    }

    public void delete(Long id) {
        languageRepository.deleteById(id);
    }
}
