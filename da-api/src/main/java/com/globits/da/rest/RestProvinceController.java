package com.globits.da.rest;

import com.globits.da.dto.request.ProvinceRequestDTO;
import com.globits.da.dto.response.ProvinceResponseDTO;
import com.globits.da.service.ProvinceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/provinces")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RestProvinceController {
    ProvinceService provinceService;

    @PostMapping
    public ProvinceResponseDTO addProvince(@Valid @RequestBody ProvinceRequestDTO provinceRequestDTO) {
        return provinceService.addProvince(provinceRequestDTO);
    }

    @PutMapping("/{id}")
    public ProvinceResponseDTO updateProvince(
            @PathVariable UUID id,
            @Valid @RequestBody ProvinceRequestDTO provinceRequestDTO) {
        return provinceService.updateProvince(id, provinceRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProvince(@PathVariable UUID id) {
        provinceService.deleteProvince(id);
    }

    @GetMapping("/{id}")
    public ProvinceResponseDTO getProvinceById(@PathVariable UUID id) {
        return provinceService.getProvinceById(id);
    }

    @GetMapping
    public List<ProvinceResponseDTO> getAllProvinces() {
        return provinceService.getAllProvinces();
    }
}
