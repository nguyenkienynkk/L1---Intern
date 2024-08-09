package com.globits.da.service.impl;

import com.globits.da.domain.TblCertificate;
import com.globits.da.domain.TblCertificateDetail;
import com.globits.da.domain.TblEmployee;
import com.globits.da.dto.request.CertificateDetailRequestDTO;
import com.globits.da.dto.response.CertificateDetailResponseDTO;
import com.globits.da.exception.HandleIllegalArgumentException;
import com.globits.da.mapper.CertificateDetailMapper;
import com.globits.da.repository.CertificateDetailRepository;
import com.globits.da.repository.CertificateRepository;
import com.globits.da.repository.TblEmployeeRepository;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.service.CertificateDetailService;
import com.globits.da.validation.ErrorValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CertificateDetailServiceImpl implements CertificateDetailService {

    private final CertificateDetailRepository certificateDetailRepository;
    private final CertificateRepository certificateRepository;
    private final TblEmployeeRepository employeeRepository;
    private final ProvinceRepository provinceRepository;
    private final CertificateDetailMapper certificateDetailMapper;

    @Transactional
    @Override
    public CertificateDetailResponseDTO addCertificateToEmployee(UUID employeeId, CertificateDetailRequestDTO requestDTO) {
        TblEmployee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new HandleIllegalArgumentException(ErrorValidation.EMPLOYEE_ID_NOT_FOUND));

        // Kiểm tra nếu văn bằng tồn tại với cùng tỉnh và còn hiệu lực
        boolean certificateExistsInProvince = certificateDetailRepository.existsByEmployeeIdAndCertificateIdAndProvinceIdAndActive(
                employeeId, requestDTO.getCertificateId(), requestDTO.getProvinceId(), true);

        if (certificateExistsInProvince) {
            throw new HandleIllegalArgumentException(ErrorValidation.CERTIFICATE_ALREADY_EXISTS_IN_PROVINCE);
        }

        // Kiểm tra nếu nhân viên đã có 3 văn bằng cùng loại còn hiệu lực
        long activeCertificateCount = certificateDetailRepository.countValidCertificatesByEmployeeId(employeeId);

        if (activeCertificateCount >= 3) {
            throw new HandleIllegalArgumentException(ErrorValidation.CERTIFICATE_EXCEEDS_LIMIT);
        }

        // Map DTO sang entity
        TblCertificateDetail detail = certificateDetailMapper.toEntity(requestDTO);
        detail.setEmployee(employee);

        // Lưu thông tin chi tiết văn bằng mới
        TblCertificateDetail savedDetail = certificateDetailRepository.save(detail);

        // Map entity sang response DTO
        return certificateDetailMapper.toResponseDTO(savedDetail);
    }


}
