package com.globits.da.mapper;

import com.globits.da.domain.TblCertificate;
import com.globits.da.domain.TblCertificateDetail;
import com.globits.da.dto.request.CertificateDetailRequestDTO;
import com.globits.da.dto.request.CertificateRequestDTO;
import com.globits.da.dto.response.CertificateDetailResponseDTO;
import com.globits.da.dto.response.CertificateResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CertificateMapper {

    CertificateResponseDTO toResponseDTO(TblCertificate certificate);

    TblCertificate toEntity(CertificateRequestDTO requestDTO);
}
