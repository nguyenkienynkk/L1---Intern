package com.globits.da.service.impl;

import com.globits.da.domain.Commune;
import com.globits.da.domain.District;
import com.globits.da.domain.Province;
import com.globits.da.dto.request.ProvinceRequestDTO;
import com.globits.da.dto.response.ProvinceResponseDTO;
import com.globits.da.mapper.CommuneMapper;
import com.globits.da.mapper.ProvinceMapper;
import com.globits.da.repository.CommuneRepository;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.service.ProvinceService;
import com.globits.da.validation.ErrorValidation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.var;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProvinceServiceImpl implements ProvinceService {
    ProvinceRepository provinceRepository;
    ProvinceMapper provinceMapper;
    CommuneRepository communeRepository;

    @Override
    @Transactional
    public ProvinceResponseDTO addProvince(ProvinceRequestDTO provinceRequestDTO) {
        Province province = provinceMapper.toEntity(provinceRequestDTO);
        Province finalProvince = province;

        List<District> districts = province.getDistricts().stream()
                .peek(d -> {
                    d.setProvince(finalProvince);

                    List<Commune> communes = d.getCommunes().stream()
                            .map(communeDTO -> {
                                if (communeDTO.getId() != null) {
                                    Commune commune = communeRepository.findById(communeDTO.getId())
                                            .orElseThrow(() -> new EntityNotFoundException(ErrorValidation.COMMUNE_ID_NOT_FOUND.getErrorMessage()));
                                    commune.setName(communeDTO.getName());
                                    commune.setDistrict(d);
                                    return commune;
                                } else {
                                    Commune commune = new Commune();
                                    commune.setName(communeDTO.getName());
                                    commune.setDistrict(d);
                                    return commune;
                                }
                            })
                            .collect(Collectors.toList());

                    d.setCommunes(communes);
                })
                .collect(Collectors.toList());

        province.setDistricts(districts);
        province = provinceRepository.save(province);

        return provinceMapper.toResponseProvince(province);
    }



    @Override
    public ProvinceResponseDTO updateProvince(UUID id, ProvinceRequestDTO provinceRequestDTO) {
        return null;
    }


//    @Override
//    public ProvinceResponseDTO updateProvince(UUID provinceId, ProvinceRequestDTO provinceRequestDTO) {
//        Province province = provinceRepository.findById(provinceId)
//                .orElseThrow(() -> new NotFoundException(ErrorValidation.PROVINCE_ID_NOT_FOUND));
//
//        province.setName(provinceRequestDTO.getName());
//
//        List<District> existingDistricts = districtRepository.findByProvinceId(provinceId);
//
//        existingDistricts.stream()
//                .filter(existing -> provinceRequestDTO.getDistricts().stream()
//                        .noneMatch(request -> request.getId() != null && request.getId().equals(existing.getId())))
//                .forEach(districtRepository::delete);
//
//        Province finalProvince = province;
//        List<District> updatedDistricts = provinceRequestDTO.getDistricts().stream()
//                .map(request -> {
//                    District district;
//                    if (request.getId() != null) {
//                        district = districtRepository.findById(request.getId())
//                                .orElseThrow(() -> new NotFoundException(ErrorValidation.DISTRICT_ID_NOT_FOUND));
//                    } else {
//                        district = new District();
//                        district.setProvince(finalProvince);
//                    }
//                    district.setName(request.getName());
//                    return districtRepository.save(district);
//                }).collect(Collectors.toList());
//
//        province.setDistricts(updatedDistricts);
//        province = provinceRepository.save(province);
//
//        return provinceMapper.toResponseProvince(province);
//    }

    @Override
    public void deleteProvince(UUID id) {
        provinceRepository.deleteById(id);
    }

    @Override
    public ProvinceResponseDTO getProvinceById(UUID id) {
        return provinceRepository.findById(id)
                .map(provinceMapper::toResponseProvince)
                .orElse(null);
    }

    @Override
    public List<ProvinceResponseDTO> getAllProvinces() {
        return provinceRepository.findAll().stream()
                .map(provinceMapper::toResponseProvince)
                .collect(Collectors.toList());
    }
}
