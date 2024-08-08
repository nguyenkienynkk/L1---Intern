package com.globits.da.service;

import com.globits.da.dto.request.DistrictRequestDTO;
import com.globits.da.dto.response.DistrictResponseDTO;

import java.util.List;
import java.util.UUID;

public interface DistrictService {
    DistrictResponseDTO addDistrict(DistrictRequestDTO requestDTO);
    DistrictResponseDTO updateDistrict(UUID id, DistrictRequestDTO requestDTO);
    DistrictResponseDTO updateDistrictWithCommunes(UUID districtId, DistrictRequestDTO districtRequestDTO);
    void deleteDistrict(UUID id);
    DistrictResponseDTO getDistrict(UUID id);
    List<DistrictResponseDTO> getDistrictsByProvince(UUID provinceId);
    List<DistrictResponseDTO> getAllDistricts();
}
