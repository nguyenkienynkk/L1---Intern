package com.globits.da.dto.request;

import lombok.Data;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class CertificateRequestDTO {
    @Size(max = 255)
    private String name;
}
