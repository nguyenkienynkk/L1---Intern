package com.globits.da.service;

import com.globits.da.dto.request.CertificateDetailRequestDTO;
import com.globits.da.dto.response.CertificateDetailResponseDTO;
import java.util.UUID;
public interface CertificateDetailService {
    CertificateDetailResponseDTO addCertificateToEmployee(UUID employeeId, CertificateDetailRequestDTO requestDTO);
}
