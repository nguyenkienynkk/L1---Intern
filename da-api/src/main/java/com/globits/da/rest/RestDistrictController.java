package com.globits.da.rest;

import com.globits.da.dto.request.DistrictRequestDTO;
import com.globits.da.dto.response.DistrictResponseDTO;
import com.globits.da.service.DistrictService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/districts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RestDistrictController {
    DistrictService districtService;

    @PostMapping
    public ResponseEntity<DistrictResponseDTO> addDistrict(@RequestBody DistrictRequestDTO requestDTO) {
        return ResponseEntity.ok(districtService.addDistrict(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DistrictResponseDTO> updateDistrict(
            @PathVariable UUID id, @RequestBody DistrictRequestDTO requestDTO) {
        return ResponseEntity.ok(districtService.updateDistrict(id, requestDTO));
    }
    @GetMapping
    public List<DistrictResponseDTO> getAllDistricts() {
        return districtService.getAllDistricts();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDistrict(@PathVariable UUID id) {
        districtService.deleteDistrict(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DistrictResponseDTO> getDistrict(@PathVariable UUID id) {
        return ResponseEntity.ok(districtService.getDistrict(id));
    }

    @GetMapping("/province/{provinceId}")
    public ResponseEntity<List<DistrictResponseDTO>> getDistrictsByProvince(@PathVariable UUID provinceId) {
        return ResponseEntity.ok(districtService.getDistrictsByProvince(provinceId));
    }
    @PutMapping("/commune/{districtId}")
    public DistrictResponseDTO updateDistrictWithCommunes(
            @PathVariable UUID districtId,
            @Valid @RequestBody DistrictRequestDTO districtRequestDTO) {
        return districtService.updateDistrictWithCommunes(districtId, districtRequestDTO);
    }
}
