package com.globits.da.dto.response;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Data;
@Data
public class CertificateDetailResponseDTO {
    private UUID id;
    private UUID certificateId;
    private String certificateName;
    private UUID provinceId;
    private String provinceName;
    private LocalDate startDay;
    private LocalDate endDay;
    private Boolean active;
    private UUID employeeId;
}
