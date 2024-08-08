package com.globits.da.service.impl;

import com.globits.da.domain.Commune;
import com.globits.da.domain.District;
import com.globits.da.dto.request.CommuneRequestDTO;
import com.globits.da.dto.response.CommuneResponseDTO;
import com.globits.da.exception.NotFoundException;
import com.globits.da.mapper.CommuneMapper;
import com.globits.da.repository.CommuneRepository;
import com.globits.da.service.CommuneService;
import com.globits.da.validation.ErrorValidation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommuneServiceImpl implements CommuneService {
    CommuneRepository communeRepository;
    CommuneMapper communeMapper;

    @Override
    public CommuneResponseDTO createCommune(CommuneRequestDTO requestDTO) {
        Commune commune = communeMapper.toEntity(requestDTO);
        commune.setDistrict(new District());
        commune = communeRepository.save(commune);
        return communeMapper.toResponse(commune);
    }

    @Override
    public CommuneResponseDTO getCommuneById(UUID communeId) {
        Commune commune = communeRepository.findById(communeId)
                .orElseThrow(() -> new NotFoundException(ErrorValidation.COMMUNE_ID_NOT_FOUND));
        return communeMapper.toResponse(commune);
    }

    @Override
    public List<CommuneResponseDTO> getAllCommunes() {
        return communeRepository.findAll().stream()
                .map(communeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CommuneResponseDTO updateCommune(UUID communeId, CommuneRequestDTO requestDTO) {
        Commune commune = communeRepository.findById(communeId)
                .orElseThrow(() -> new NotFoundException(ErrorValidation.COMMUNE_ID_NOT_FOUND));
        communeMapper.updateEntityFromDTO(requestDTO, commune);
        commune = communeRepository.save(commune);
        return communeMapper.toResponse(commune);
    }

    @Override
    public void deleteCommune(UUID communeId) {
        Commune commune = communeRepository.findById(communeId)
                .orElseThrow(() -> new NotFoundException(ErrorValidation.COMMUNE_ID_NOT_FOUND));
        communeRepository.delete(commune);
    }
}
