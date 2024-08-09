package com.globits.da.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeRequestDTO {

    @Min(value = 0, message = "Age must not be negative")
    private Integer age;

    @NotBlank(message = "Code is required")
    private String code;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Phone number is required")
    private String phone;

    @NotNull(message = "Commune ID is required")
    private UUID communeId;

    @NotNull(message = "District ID is required")
    private UUID districtId;

    @NotNull(message = "Province ID is required")
    private UUID provinceId;
}
