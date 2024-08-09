package com.globits.da.mapper;

import com.globits.da.domain.Commune;
import com.globits.da.domain.District;
import com.globits.da.domain.Province;
import com.globits.da.dto.request.CommuneRequestDTO;
import com.globits.da.dto.request.DistrictRequestDTO;
import com.globits.da.dto.request.ProvinceRequestDTO;
import com.globits.da.dto.response.CommuneResponseDTO;
import com.globits.da.dto.response.ProvinceResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
@Mapper(componentModel = "spring",uses = {DistrictMapper.class})
public interface ProvinceMapper {

    @Mapping(target = "districts", source = "districts")
    ProvinceResponseDTO toResponseProvince(Province province);

    Province toEntity(ProvinceRequestDTO requestDTO);

    @Mapping(target = "districtId", source = "district.id")
    CommuneResponseDTO toResponseCommune(Commune commune);

    void updateEntityFromDTO(ProvinceRequestDTO dto, @MappingTarget Province entity);
    void updateEntityFromDTO(DistrictRequestDTO dto, @MappingTarget District entity);
    void updateEntityFromDTO(CommuneRequestDTO dto, @MappingTarget Commune entity);
}
