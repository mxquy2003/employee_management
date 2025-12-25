package com.example.employee_management.controller;

import com.example.employee_management.dto.request.EmployeeRequest;
import com.example.employee_management.dto.response.EmployeeResponse;
import com.example.employee_management.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<EmployeeResponse> getAll() {
        return employeeService.getAll();
    }

    @PostMapping
    public void create(@Valid @RequestBody EmployeeRequest request) {
        employeeService.create(request);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id,
                       @Valid @RequestBody EmployeeRequest request) {
        employeeService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        employeeService.delete(id);
    }
}
