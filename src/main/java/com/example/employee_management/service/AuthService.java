package com.example.employee_management.service;

import com.example.employee_management.dto.request.LoginRequest;
import com.example.employee_management.entity.User;
import com.example.employee_management.repository.UserRepository;
import com.example.employee_management.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public String login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        String role = (user.getRole() == null) ? "USER" : user.getRole();
        return jwtUtil.generateToken(user.getUsername(), role);
    }
}