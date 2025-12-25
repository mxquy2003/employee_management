package com.example.employee_management.service;

import com.example.employee_management.entity.Language;
import com.example.employee_management.repository.EmployeeRepository;
import com.example.employee_management.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageService {

    private final LanguageRepository languageRepository;
    private final EmployeeRepository employeeRepository; // Inject thêm Repository nhân viên

    public List<Language> getAll() {
        return languageRepository.findAll();
    }

    public Language create(Language language) {
        if (languageRepository.existsByNameAndLevel(language.getName(), language.getLevel())) {
            throw new RuntimeException("Ngôn ngữ với trình độ này đã tồn tại!");
        }
        return languageRepository.save(language);
    }

    public Language update(Long id, Language language) {
        Language existing = languageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("K tìm được ngôn ngữ"));


        if(languageRepository.existsByNameAndLevelAndIdNot(language.getName(), language.getLevel(), id)){
            throw new RuntimeException("Ngôn ngữ đang tồn tại ở bản ghi khac");
        }


        existing.setName(language.getName());
        existing.setLevel(language.getLevel());

        return languageRepository.save(existing);
    }

    public void delete(Long id) {
        if (!languageRepository.existsById(id)) {
            throw new RuntimeException("Ngôn ngữ không tồn tại!");
        }

        if (employeeRepository.existsByLanguagesId(id)) {
            throw new RuntimeException("Không thể xóa ngôn ngữ đang được nhân viên sử dụng!");
        }

        languageRepository.deleteById(id);
    }
}