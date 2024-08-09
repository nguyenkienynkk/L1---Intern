package com.globits.da.rest;

import com.globits.da.dto.request.CertificateDetailRequestDTO;
import com.globits.da.dto.response.CertificateDetailResponseDTO;
import com.globits.da.service.CertificateDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/certificate-details")
@RequiredArgsConstructor
public class RestCertificateDetailController {

    private final CertificateDetailService certificateDetailService;

    /**
     * Thêm một chứng chỉ mới cho nhân viên.
     *
     * @param employeeId ID của nhân viên.
     * @param requestDTO DTO yêu cầu chứa thông tin chứng chỉ chi tiết.
     * @return DTO phản hồi chứa thông tin chứng chỉ chi tiết vừa được thêm.
     */
    @PostMapping("/{employeeId}")
    public ResponseEntity<CertificateDetailResponseDTO> addCertificateToEmployee(
            @PathVariable UUID employeeId,
            @Valid @RequestBody CertificateDetailRequestDTO requestDTO) {
        CertificateDetailResponseDTO responseDTO = certificateDetailService.addCertificateToEmployee(employeeId, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
