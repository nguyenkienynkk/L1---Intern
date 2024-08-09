package com.globits.da.mapper;

import com.globits.da.domain.Employee;
import com.globits.da.domain.TblEmployee;
import com.globits.da.dto.EmployeeDTO;
import com.globits.da.dto.request.EmployeeRequestDTO;
import com.globits.da.dto.response.EmployeeResponseDTO;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    //Employee #16
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
