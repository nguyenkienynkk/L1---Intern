package com.globits.da.mapper;

import com.globits.da.domain.TblCertificateDetail;
import com.globits.da.dto.request.CertificateDetailRequestDTO;
import com.globits.da.dto.response.CertificateDetailResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CertificateDetailMapper {

    @Mapping(source = "certificate.id", target = "certificateId")
    @Mapping(source = "certificate.name", target = "certificateName")
    @Mapping(source = "province.id", target = "provinceId")
    @Mapping(source = "province.name", target = "provinceName")
    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(source = "active", target = "active")
    CertificateDetailResponseDTO toResponseDTO(TblCertificateDetail certificateDetail);

    @Mapping(source = "certificateId", target = "certificate.id")
    @Mapping(source = "provinceId", target = "province.id")
    TblCertificateDetail toEntity(CertificateDetailRequestDTO requestDTO);
}
