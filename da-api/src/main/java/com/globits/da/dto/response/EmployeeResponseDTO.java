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
public class EmployeeResponseDTO {
    private UUID id;
    private Integer age;
    private String code;
    private String email;
    private String name;
    private String phone;
    private UUID communeId;
    private UUID districtId;
    private UUID provinceId;
    private String communeName;
    private String districtName;
    private String provinceName;
}
