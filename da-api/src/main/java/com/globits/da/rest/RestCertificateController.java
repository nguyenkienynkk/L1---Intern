package com.globits.da.rest;

import com.globits.da.dto.request.CertificateRequestDTO;
import com.globits.da.dto.response.CertificateResponseDTO;
import com.globits.da.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/certificates")
@RequiredArgsConstructor
public class RestCertificateController {

    private final CertificateService certificateService;

    @PostMapping
    public ResponseEntity<CertificateResponseDTO> createCertificate(@Valid @RequestBody CertificateRequestDTO requestDTO) {
        CertificateResponseDTO responseDTO = certificateService.createCertificate(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
