package com.globits.da.rest;

import com.globits.da.Constants;
import com.globits.da.dto.EmployeeDTO;
import com.globits.da.dto.search.EmployeeSearchDTO;
import com.globits.da.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class RestEmployeeController {
    private final EmployeeService employeeService;

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
