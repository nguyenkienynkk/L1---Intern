package com.globits.da.service;

import com.globits.da.dto.request.ProvinceRequestDTO;
import com.globits.da.dto.response.ProvinceResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public interface ProvinceService {
    ProvinceResponseDTO addProvince(ProvinceRequestDTO provinceRequestDTO);

    ProvinceResponseDTO updateProvince(UUID id, ProvinceRequestDTO provinceRequestDTO);

    void deleteProvince(UUID id);

    ProvinceResponseDTO getProvinceById(UUID id);

    List<ProvinceResponseDTO> getAllProvinces();
}
