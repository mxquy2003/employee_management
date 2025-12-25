package com.example.employee_management.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class EmployeeRequest {
    @NotBlank(message = "Họ tên không được để trống")
    @Size(min = 2, max = 100, message = "Họ tên phải từ 2 đến 100 ký tự")
    private String name;

    @NotNull(message = "Ngày sinh không được để trống")
    @Past(message = "Ngày sinh phải là ngày trong quá khứ")
    private LocalDate dob;

    @Size(max = 255, message = "Địa chỉ không được quá 255 ký tự")
    private String address;

    @Pattern(regexp = "^\\d{10}$", message = "Số điện thoại phải bao gồm đúng 10 chữ số")
    private String phone;
    private Set<Long> languageIds;
    private Set<Long> certificateIds;
}
