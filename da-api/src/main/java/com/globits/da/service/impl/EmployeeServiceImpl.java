package com.globits.da.service.impl;

import com.globits.da.Constants;
import com.globits.da.domain.Commune;
import com.globits.da.domain.District;
import com.globits.da.domain.Employee;
import com.globits.da.domain.Province;
import com.globits.da.domain.TblEmployee;
import com.globits.da.dto.EmployeeDTO;
import com.globits.da.dto.request.EmployeeRequestDTO;
import com.globits.da.dto.request.TblEmployeeImportDTO;
import com.globits.da.dto.response.EmployeeResponseDTO;
import com.globits.da.dto.search.EmployeeSearchDTO;
import com.globits.da.exception.FileExcelException;
import com.globits.da.exception.HandleIllegalArgumentException;
import com.globits.da.exception.NotFoundException;
import com.globits.da.mapper.EmployeeMapper;
import com.globits.da.mapper.TblEmployeeMapper;
import com.globits.da.repository.CommuneRepository;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.repository.EmployeeRepository;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.repository.TblEmployeeRepository;
import com.globits.da.service.EmployeeService;
import com.globits.da.utils.SetFileExcelValue;
import com.globits.da.validation.ErrorValidation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository employeeRepository;
    TblEmployeeRepository tblEmployeeRepository;
    EmployeeMapper employeeMapper;
    TblEmployeeMapper tblEmployeeMapper;
    SetFileExcelValue setFileExcelValue;
    ProvinceRepository provinceRepository;
    DistrictRepository districtRepository;
    CommuneRepository communeRepository;

    @Override
    public List<EmployeeDTO> getAll() {
        return employeeMapper.toDto(employeeRepository.findAll());
    }

    @Override
    public List<EmployeeResponseDTO> getAllTblEmployee() {
        List<TblEmployee> employees = tblEmployeeRepository.findAll();
        return employees.stream()
                .map(tblEmployeeMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO) {
        if (employeeRequestDTO.getProvinceId() == null ||
                employeeRequestDTO.getDistrictId() == null ||
                employeeRequestDTO.getCommuneId() == null) {
            throw new IllegalArgumentException("Province ID, District ID, and Commune ID must not be null");
        }

        validateLocation(employeeRequestDTO.getProvinceId(), employeeRequestDTO.getDistrictId(), employeeRequestDTO.getCommuneId());

        TblEmployee employee = tblEmployeeMapper.toEntityEmployee(employeeRequestDTO);
        employee = tblEmployeeRepository.save(employee);

        return tblEmployeeMapper.toResponseDTO(employee);
    }


    @Transactional
    @Override
    public EmployeeResponseDTO updateEmployee(UUID id, EmployeeRequestDTO employeeRequestDTO) {
        TblEmployee employee = tblEmployeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorValidation.EMPLOYEE_ID_NOT_FOUND));

        if (employeeRequestDTO.getProvinceId() != null &&
                employeeRequestDTO.getDistrictId() != null &&
                employeeRequestDTO.getCommuneId() != null) {

            validateLocation(employeeRequestDTO.getProvinceId(), employeeRequestDTO.getDistrictId(), employeeRequestDTO.getCommuneId());

            Province province = provinceRepository.findById(employeeRequestDTO.getProvinceId())
                    .orElseThrow(() -> new NotFoundException(ErrorValidation.PROVINCE_ID_NOT_FOUND));
            District district = districtRepository.findById(employeeRequestDTO.getDistrictId())
                    .orElseThrow(() -> new NotFoundException(ErrorValidation.DISTRICT_ID_NOT_FOUND));
            Commune commune = communeRepository.findById(employeeRequestDTO.getCommuneId())
                    .orElseThrow(() -> new NotFoundException(ErrorValidation.COMMUNE_ID_NOT_FOUND));

            if (!district.getProvince().getId().equals(province.getId())) {
                throw new IllegalArgumentException("District ID " + employeeRequestDTO.getDistrictId() + " does not belong to the specified Province ID " + employeeRequestDTO.getProvinceId());
            }

            if (!commune.getDistrict().getId().equals(district.getId())) {
                throw new IllegalArgumentException("Commune ID " + employeeRequestDTO.getCommuneId() + " does not belong to the specified District ID " + employeeRequestDTO.getDistrictId());
            }

            employee.setProvince(province);
            employee.setDistrict(district);
            employee.setCommune(commune);
        } else {
            if (employeeRequestDTO.getProvinceId() != null ||
                    employeeRequestDTO.getDistrictId() != null ||
                    employeeRequestDTO.getCommuneId() != null) {
                throw new IllegalArgumentException("All location fields must be provided to update location data");
            }
        }
        if (employeeRequestDTO.getCode() != null) {
            employee.setCode(employeeRequestDTO.getCode());
        }
        if (employeeRequestDTO.getEmail() != null) {
            employee.setEmail(employeeRequestDTO.getEmail());
        }
        if (employeeRequestDTO.getName() != null) {
            employee.setName(employeeRequestDTO.getName());
        }
        if (employeeRequestDTO.getPhone() != null) {
            employee.setPhone(employeeRequestDTO.getPhone());
        }
        if (employeeRequestDTO.getAge() != null) {
            employee.setAge(employeeRequestDTO.getAge());
        }

        employee = tblEmployeeRepository.save(employee);

        return tblEmployeeMapper.toResponseDTO(employee);
    }

    private void validateLocation(UUID provinceId, UUID districtId, UUID communeId) {
        District district = districtRepository.findById(districtId)
                .orElseThrow(() -> new NotFoundException(ErrorValidation.DISTRICT_ID_NOT_FOUND));

        if (!district.getProvince().getId().equals(provinceId)) {
            throw new HandleIllegalArgumentException(ErrorValidation.DISTRICT_NOT_BELONG_TO_PROVINCE);
        }

        Commune commune = communeRepository.findById(communeId)
                .orElseThrow(() -> new HandleIllegalArgumentException(ErrorValidation.COMMUNE_ID_NOT_FOUND));

        if (!commune.getDistrict().getId().equals(districtId)) {
            throw new HandleIllegalArgumentException(ErrorValidation.COMMUNE_NOT_BELONG_TO_DISTRICT);
        }
    }

    @Transactional
    @Override
    public void importEmployees(MultipartFile file) throws IOException, InvalidFormatException {
        List<TblEmployeeImportDTO> employeeDTOs = readEmployeeDataFromExcel(file);
        List<String> errors = new ArrayList<>();

        for (int i = 0; i < employeeDTOs.size(); i++) {
            TblEmployeeImportDTO dto = employeeDTOs.get(i);
            try {
                validateEmployeeData(dto);
                saveEmployee(dto);
            } catch (Exception e) {
                errors.add("Dòng " + (i + 1) + ": " + e.getMessage());
            }
        }

        if (!errors.isEmpty()) {
            throw new RuntimeException("Lỗi khi import dữ liệu: " + String.join(", ", errors));
        }
    }
    private List<TblEmployeeImportDTO> readEmployeeDataFromExcel(MultipartFile file) throws IOException, InvalidFormatException {
        List<TblEmployeeImportDTO> employees = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        DataFormatter dataFormatter = new DataFormatter();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);

            TblEmployeeImportDTO employee = new TblEmployeeImportDTO();
            employee.setName(dataFormatter.formatCellValue(row.getCell(0)));
            employee.setProvinceName(dataFormatter.formatCellValue(row.getCell(1)));
            employee.setDistrictName(dataFormatter.formatCellValue(row.getCell(2)));
            employee.setCommuneName(dataFormatter.formatCellValue(row.getCell(3)));

            employees.add(employee);
        }

        workbook.close();
        return employees;
    }

    private void validateEmployeeData(TblEmployeeImportDTO dto) {
        if (dto.getProvinceName() == null || dto.getDistrictName() == null || dto.getCommuneName() == null) {
            throw new IllegalArgumentException("Tỉnh, huyện, xã không được để trống");
        }

        Province province = provinceRepository.findByName(dto.getProvinceName())
                .orElseThrow(() -> new IllegalArgumentException("Tỉnh không hợp lệ: " + dto.getProvinceName()));

        District district = districtRepository.findByNameAndProvince(dto.getDistrictName(), province)
                .orElseThrow(() -> new IllegalArgumentException("Huyện không hợp lệ hoặc không thuộc tỉnh: " + dto.getDistrictName()));

        Commune commune = communeRepository.findByNameAndDistrict(dto.getCommuneName(), district)
                .orElseThrow(() -> new IllegalArgumentException("Xã không hợp lệ hoặc không thuộc huyện: " + dto.getCommuneName()));
    }


    private void saveEmployee(TblEmployeeImportDTO dto) {
        TblEmployee employee = new TblEmployee();
        employee.setName(dto.getName());

        Province province = provinceRepository.findByName(dto.getProvinceName())
                .orElseThrow(() -> new IllegalArgumentException("Tỉnh không hợp lệ: " + dto.getProvinceName()));
        District district = districtRepository.findByNameAndProvince(dto.getDistrictName(), province)
                .orElseThrow(() -> new IllegalArgumentException("Huyện không hợp lệ hoặc không thuộc tỉnh: " + dto.getDistrictName()));
        Commune commune = communeRepository.findByNameAndDistrict(dto.getCommuneName(), district)
                .orElseThrow(() -> new IllegalArgumentException("Xã không hợp lệ hoặc không thuộc huyện: " + dto.getCommuneName()));

        employee.setProvince(province);
        employee.setDistrict(district);
        employee.setCommune(commune);

        tblEmployeeRepository.save(employee);
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
