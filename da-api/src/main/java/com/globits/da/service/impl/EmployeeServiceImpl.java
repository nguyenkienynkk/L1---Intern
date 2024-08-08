package com.globits.da.service.impl;

import com.globits.da.Constants;
import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeDTO;
import com.globits.da.dto.search.EmployeeSearchDTO;
import com.globits.da.exception.FileExcelException;
import com.globits.da.mapper.EmployeeMapper;
import com.globits.da.repository.EmployeeRepository;
import com.globits.da.service.EmployeeService;
import com.globits.da.utils.SetFileExcelValue;
import com.globits.da.validation.ErrorValidation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository employeeRepository;
    EmployeeMapper employeeMapper;
    SetFileExcelValue setFileExcelValue;

    @Override
    public List<EmployeeDTO> getAll() {
        return employeeMapper.toDto(employeeRepository.findAll());
    }

    @Override
    public List<EmployeeDTO> searchEmployees(EmployeeSearchDTO searchDTO) {
        String keyword = searchDTO.getKeyword() != null ? searchDTO.getKeyword().trim().toLowerCase() : null;
        List<Employee> employees = employeeRepository.searchEmployees(keyword);
        return employees.stream().map(employeeMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void exportFileExcel() {
        try {
            Workbook workbook = setFileExcelValue.createFileExcel(Constants.EXCEL_FILE_PATH);
            Set<EmployeeDTO> employeeDtoSet = employeeRepository.getAll();
            Sheet sheet = workbook.createSheet("Employee");
            int rowIndex = 0;
            setFileExcelValue.writeHeaderRow(sheet , rowIndex);
            rowIndex++;
            for(EmployeeDTO employeeDto : employeeDtoSet){
                Row row = sheet.createRow(rowIndex);
                setFileExcelValue.writeDataRows(employeeDto , row);
                rowIndex++;
            }
            setFileExcelValue.writeFileExcel(workbook ,Constants.EXCEL_FILE_PATH);
        }catch (IOException e){
            throw new FileExcelException(ErrorValidation.CAN_NOT_EXPORT_EXCEL_FILE);
        }
    }

}
