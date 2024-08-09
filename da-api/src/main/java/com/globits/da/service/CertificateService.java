package com.globits.da.service;


import com.globits.da.dto.request.CertificateRequestDTO;
import com.globits.da.dto.response.CertificateResponseDTO;
public interface CertificateService {
    CertificateResponseDTO createCertificate(CertificateRequestDTO requestDTO);
}
