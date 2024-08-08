package com.globits.da.mapper;

import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeDTO;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEntity(EmployeeDTO employeeDTO);

    List<EmployeeDTO> toDto(List<Employee> employees);
    default EmployeeDTO convertToDto(@NotNull Employee employee) {
        return EmployeeDTO.builder()
                .id(employee.getId())
                .code(employee.getCode())
                .name(employee.getName())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .age(employee.getAge())
                .build();
    }

}
