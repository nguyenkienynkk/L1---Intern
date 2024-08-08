package com.globits.da.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DistrictResponseDTO {
    private UUID id;
    private String name;
    private UUID provinceId;
    private String provinceName;
    private List<CommuneResponseDTO> communes;
}
