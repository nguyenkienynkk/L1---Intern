package com.globits.da.rest;

import com.globits.da.Constants;
import com.globits.da.dto.ApiResponse;
import com.globits.da.dto.EmployeeDTO;
import com.globits.da.dto.request.EmployeeRequestDTO;
import com.globits.da.dto.response.EmployeeResponseDTO;
import com.globits.da.dto.search.EmployeeSearchDTO;
import com.globits.da.service.EmployeeService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employee")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class RestEmployeeController {
    EmployeeService employeeService;

    public RestEmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeDTO> getAll() {
        return employeeService.getAll();
    }

    @PostMapping("/search")
    public List<EmployeeDTO> searchEmployees(@RequestBody EmployeeSearchDTO searchDTO) {
        return employeeService.searchEmployees(searchDTO);
    }
    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<List<EmployeeResponseDTO>>> getAllEmployees() {
        List<EmployeeResponseDTO> employeeList = employeeService.getAllTblEmployee();
        ApiResponse<List<EmployeeResponseDTO>> apiResponse = ApiResponse.<List<EmployeeResponseDTO>>builder()
                .data(employeeList)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>> createEmployee(@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        if (employeeRequestDTO.getProvinceId() == null ||
                employeeRequestDTO.getDistrictId() == null ||
                employeeRequestDTO.getCommuneId() == null) {
            ApiResponse<EmployeeResponseDTO> apiResponse = ApiResponse.<EmployeeResponseDTO>builder()
                    .errorCode(HttpStatus.BAD_REQUEST.name())
                    .errorMessage("Province ID, District ID, and Commune ID must not be null")
                    .build();
            return ResponseEntity.badRequest().body(apiResponse);
        }
        EmployeeResponseDTO response = employeeService.createEmployee(employeeRequestDTO);
        ApiResponse<EmployeeResponseDTO> apiResponse = ApiResponse.<EmployeeResponseDTO>builder()
                .data(response)
                .build();
        return ResponseEntity.ok(apiResponse);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>> updateEmployee(
            @Valid
            @PathVariable UUID id,
            @RequestBody EmployeeRequestDTO employeeRequestDTO) {

        EmployeeResponseDTO response = employeeService.updateEmployee(id, employeeRequestDTO);
        ApiResponse<EmployeeResponseDTO> apiResponse = ApiResponse.<EmployeeResponseDTO>builder()
                .data(response)
                .errorCode(null)
                .errorMessage(null)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return "Delete thành công";
    }

    @GetMapping("/export")
    public ResponseEntity<String> exportEmployee() {
        employeeService.exportFileExcel();
        return ResponseEntity.ok(Constants.EXPORT_EXCEL_SUCCESSFUL);
    }

}
