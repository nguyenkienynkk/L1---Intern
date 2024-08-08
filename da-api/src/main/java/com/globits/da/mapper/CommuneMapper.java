package com.globits.da.mapper;

import com.globits.da.domain.Commune;
import com.globits.da.dto.request.CommuneRequestDTO;
import com.globits.da.dto.response.CommuneResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CommuneMapper {
    @Mapping(target = "id", ignore = true)
    Commune toEntity(CommuneRequestDTO dto);
    @Mapping(target = "districtId", source = "district.id")
    CommuneResponseDTO toResponse(Commune commune);

    void updateEntityFromDTO(CommuneRequestDTO dto, @MappingTarget Commune commune);
}
