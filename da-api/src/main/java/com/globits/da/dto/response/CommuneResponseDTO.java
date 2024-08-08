package com.globits.da.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommuneResponseDTO {
    private UUID id;
    private String name;
    private UUID districtId;
    private String districtName;
}