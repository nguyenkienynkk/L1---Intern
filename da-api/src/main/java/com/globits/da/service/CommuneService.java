package com.globits.da.service;

import com.globits.da.dto.request.CommuneRequestDTO;
import com.globits.da.dto.response.CommuneResponseDTO;

import java.util.List;
import java.util.UUID;

public interface CommuneService {
    CommuneResponseDTO createCommune(CommuneRequestDTO requestDTO);
    CommuneResponseDTO getCommuneById(UUID communeId);
    List<CommuneResponseDTO> getAllCommunes();
    CommuneResponseDTO updateCommune(UUID communeId, CommuneRequestDTO requestDTO);
    void deleteCommune(UUID communeId);
    List<CommuneResponseDTO> getCommunesByDistrict(UUID districtId);
}
