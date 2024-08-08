package com.globits.da.service;

import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeDTO;
import com.globits.da.dto.search.EmployeeSearchDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface EmployeeService {
    List<EmployeeDTO> getAll();
    List<EmployeeDTO> searchEmployees(EmployeeSearchDTO employeeSearchDTO);
    void deleteEmployee(int id);
    void exportFileExcel();
}
