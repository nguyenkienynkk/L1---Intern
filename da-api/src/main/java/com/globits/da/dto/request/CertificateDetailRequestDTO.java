package com.globits.da.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CertificateDetailRequestDTO {
    private UUID certificateId;
    private UUID employeeId;
    private UUID provinceId;
    private LocalDate startDay;
    private LocalDate endDay;
    private Boolean active;
}

