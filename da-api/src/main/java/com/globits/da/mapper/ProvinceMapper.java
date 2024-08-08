package com.globits.da.mapper;

import com.globits.da.domain.Commune;
import com.globits.da.domain.District;
import com.globits.da.domain.Province;
import com.globits.da.dto.request.CommuneRequestDTO;
import com.globits.da.dto.request.DistrictRequestDTO;
import com.globits.da.dto.request.ProvinceRequestDTO;
import com.globits.da.dto.response.CommuneResponseDTO;
import com.globits.da.dto.response.DistrictResponseDTO;
import com.globits.da.dto.response.ProvinceResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProvinceMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "districts", source = "districts")
    Province toEntityProvince(ProvinceRequestDTO dto);

    @Mapping(target = "districts", source = "districts")
    ProvinceResponseDTO toResponseProvince(Province province);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "province.id", source = "provinceId")
    @Mapping(target = "communes", source = "communes")
    District toEntityDistrict(DistrictRequestDTO dto);

    @Mapping(target = "provinceName", source = "province.name")
    @Mapping(target = "communes", source = "communes")
    DistrictResponseDTO toResponseDistrict(District district);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "district.id", source = "districtId")
    Commune toEntityCommune(CommuneRequestDTO dto);

    @Mapping(target = "districtName", source = "district.name")
    CommuneResponseDTO toResponseCommune(Commune commune);

    void updateEntityFromDTO(ProvinceRequestDTO dto, @MappingTarget Province entity);
    void updateEntityFromDTO(DistrictRequestDTO dto, @MappingTarget District entity);
    void updateEntityFromDTO(CommuneRequestDTO dto, @MappingTarget Commune entity);
}
