package com.globits.da.service;

import com.globits.da.dto.EmployeeDTO;
import com.globits.da.dto.request.EmployeeRequestDTO;
import com.globits.da.dto.request.TblEmployeeImportDTO;
import com.globits.da.dto.response.EmployeeResponseDTO;
import com.globits.da.dto.search.EmployeeSearchDTO;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public interface EmployeeService {
    List<EmployeeDTO> getAll();
    List<EmployeeResponseDTO> getAllTblEmployee();
    EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO);
    List<EmployeeDTO> searchEmployees(EmployeeSearchDTO employeeSearchDTO);
    void deleteEmployee(int id);
    void exportFileExcel();
    EmployeeResponseDTO updateEmployee(UUID id, EmployeeRequestDTO employeeRequestDTO);
    void importEmployees(MultipartFile file) throws IOException, InvalidFormatException;
}
