package com.globits.da.mapper;

import com.globits.da.domain.Commune;
import com.globits.da.dto.request.CommuneRequestDTO;
import com.globits.da.dto.response.CommuneResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommuneMapper {
    @Mapping(target = "district.id", source = "districtId")
    Commune toEntity(CommuneRequestDTO dto);

    @Mapping(target = "districtId", source = "district.id")
    @Mapping(target = "districtName",source = "district.name")
    CommuneResponseDTO toResponse(Commune commune);
}
