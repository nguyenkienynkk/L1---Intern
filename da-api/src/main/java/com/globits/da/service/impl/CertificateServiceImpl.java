package com.globits.da.service.impl;

import com.globits.da.domain.TblCertificate;
import com.globits.da.dto.request.CertificateRequestDTO;
import com.globits.da.dto.response.CertificateResponseDTO;
import com.globits.da.exception.HandleIllegalArgumentException;
import com.globits.da.mapper.CertificateMapper;
import com.globits.da.repository.CertificateRepository;
import com.globits.da.service.CertificateService;
import com.globits.da.validation.ErrorValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {
    private final CertificateRepository certificateRepository;
    private final CertificateMapper certificateMapper;

    @Transactional
    @Override
    public CertificateResponseDTO createCertificate(CertificateRequestDTO requestDTO) {
        boolean certificateExists = certificateRepository.existsByName(requestDTO.getName());
        if (certificateExists) {
            throw new HandleIllegalArgumentException(ErrorValidation.CERTIFICATE_ALREADY_EXISTS);
        }
        TblCertificate certificate = certificateMapper.toEntity(requestDTO);
        TblCertificate savedCertificate = certificateRepository.save(certificate);
        return certificateMapper.toResponseDTO(savedCertificate);
    }
}
