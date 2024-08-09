package com.globits.da.mapper;

import com.globits.da.domain.TblEmployee;
import com.globits.da.dto.request.EmployeeRequestDTO;
import com.globits.da.dto.response.EmployeeResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TblEmployeeMapper {

    @Mapping(target = "province.id", source = "provinceId")
    @Mapping(target = "district.id", source = "districtId")
    @Mapping(target = "commune.id", source = "communeId")
    TblEmployee toEntityEmployee(EmployeeRequestDTO requestDTO);

    @Mapping(target = "provinceId", source = "province.id")
    @Mapping(target = "districtId", source = "district.id")
    @Mapping(target = "communeId", source = "commune.id")
    @Mapping(target = "provinceName", source = "province.name")
    @Mapping(target = "districtName", source = "district.name")
    @Mapping(target = "communeName", source = "commune.name")
    EmployeeResponseDTO toResponseDTO(TblEmployee employee);

    @Mapping(target = "province.id", source = "provinceId")
    @Mapping(target = "district.id", source = "districtId")
    @Mapping(target = "commune.id", source = "communeId")
    void updateEmployeeFromDto(EmployeeRequestDTO dto, @MappingTarget TblEmployee entity);
}
