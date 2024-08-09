package com.globits.da.service.impl;

import com.globits.da.domain.District;
import com.globits.da.domain.Province;
import com.globits.da.dto.request.DistrictRequestDTO;
import com.globits.da.dto.response.DistrictResponseDTO;
import com.globits.da.mapper.DistrictMapper;
import com.globits.da.repository.CommuneRepository;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.service.DistrictService;
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
public class DistrictServiceImpl implements DistrictService {
    DistrictRepository districtRepository;
    ProvinceRepository provinceRepository;
    DistrictMapper districtMapper;
    CommuneRepository communeRepository;

    @Override
    public DistrictResponseDTO addDistrict(DistrictRequestDTO requestDTO) {
        Province province = provinceRepository.findById(requestDTO.getProvinceId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Province ID"));
        District district = districtMapper.toEntity(requestDTO);
        district.setProvince(province);
        district = districtRepository.save(district);
        return districtMapper.toResponse(district);
    }
    @Override
    public List<DistrictResponseDTO> getAllDistricts() {
        List<District> districts = districtRepository.findAll();
        return districts.stream()
                .map(districtMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DistrictResponseDTO updateDistrict(UUID id, DistrictRequestDTO requestDTO) {
        District district = districtRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid District ID"));
        Province province = provinceRepository.findById(requestDTO.getProvinceId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Province ID"));
        district.setName(requestDTO.getName());
        district.setProvince(province);
        district = districtRepository.save(district);
        return districtMapper.toResponse(district);
    }

    @Override
    public DistrictResponseDTO updateDistrictWithCommunes(UUID districtId, DistrictRequestDTO districtRequestDTO) {
        return null;
    }

    @Override
    public void deleteDistrict(UUID id) {
        District district = districtRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid District ID"));
        districtRepository.delete(district);
    }

    @Override
    public DistrictResponseDTO getDistrict(UUID id) {
        District district = districtRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid District ID"));
        return districtMapper.toResponse(district);
    }

    @Override
    public List<DistrictResponseDTO> getDistrictsByProvince(UUID provinceId) {
        List<District> districts = districtRepository.findByProvinceId(provinceId);
        return districts.stream()
                .map(districtMapper::toResponse)
                .collect(Collectors.toList());
    }
//    @Override
//    public DistrictResponseDTO updateDistrictWithCommunes(UUID districtId, DistrictRequestDTO districtRequestDTO) {
//        District district = districtRepository.findById(districtId)
//                .orElseThrow(() -> new NotFoundException(ErrorValidation.DISTRICT_ID_NOT_FOUND));
//
//        district.setName(districtRequestDTO.getName());
//
//        // Tạo set ID của các xã trong yêu cầu
//        Set<UUID> requestedCommuneIds = districtRequestDTO.getCommunes().stream()
//                .map(CommuneRequestDTO::getId)
//                .collect(Collectors.toSet());
//
//        // Xóa các xã không còn trong yêu cầu
//        district.getCommunes().removeIf(commune -> !requestedCommuneIds.contains(commune.getId()));
//
//        // Cập nhật hoặc thêm các xã mới
//        for (CommuneRequestDTO communeDTO : districtRequestDTO.getCommunes()) {
//            if (communeDTO.getId() != null) {
//                Commune commune = communeRepository.findById(communeDTO.getId())
//                        .orElseThrow(() -> new NotFoundException(ErrorValidation.COMMUNE_ID_NOT_FOUND));
//                commune.setName(communeDTO.getName());
//                communeRepository.save(commune);
//            } else {
//                Commune newCommune = new Commune();
//                newCommune.setName(communeDTO.getName());
//                newCommune.setDistrict(district);
//                communeRepository.save(newCommune);
//            }
//        }
//
//        district = districtRepository.save(district);
//        return districtMapper.toResponse(district);
//    }
}
