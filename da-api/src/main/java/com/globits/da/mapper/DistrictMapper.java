package com.globits.da.mapper;

import com.globits.da.domain.District;
import com.globits.da.dto.request.DistrictRequestDTO;
import com.globits.da.dto.response.DistrictResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CommuneMapper.class})
public interface DistrictMapper {
    @Mapping(source = "province.id", target = "provinceId")
    @Mapping(source = "province.name", target = "provinceName")
    DistrictResponseDTO toResponse(District district);

    @Mapping(source = "provinceId", target = "province.id")
    District toEntity(DistrictRequestDTO dto);
}
