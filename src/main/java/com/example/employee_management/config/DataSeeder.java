package com.example.employee_management.config;

import com.example.employee_management.entity.Certificate;
import com.example.employee_management.entity.Language;
import com.example.employee_management.entity.User;
import com.example.employee_management.repository.CertificateRepository;
import com.example.employee_management.repository.LanguageRepository;
import com.example.employee_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final LanguageRepository languageRepository;
    private final CertificateRepository certificateRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ADMIN");
            userRepository.save(admin);
        }
        if (userRepository.findByUsername("user").isEmpty()) {
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setRole("USER");
            userRepository.save(user);
        }

        if (languageRepository.count() == 0) {
            createLang("English", "C1");
            createLang("Japanese", "N2");
            createLang("Vietnamese", "Native");
        }

        if (certificateRepository.count() == 0) {
            createCert("PMP");
            createCert("AWS Solutions Architect");
            createCert("Scrum Master");
        }
    }

    private void createLang(String name, String level) {
        Language l = new Language();
        l.setName(name);
        l.setLevel(level);
        languageRepository.save(l);
    }

    private void createCert(String name) {
        Certificate c = new Certificate();
        c.setName(name);
        certificateRepository.save(c);
    }
}