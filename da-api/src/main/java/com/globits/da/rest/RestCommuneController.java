package com.globits.da.rest;

import com.globits.da.dto.request.CommuneRequestDTO;
import com.globits.da.dto.response.CommuneResponseDTO;
import com.globits.da.service.CommuneService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/communes")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RestCommuneController {
    CommuneService communeService;

    @PostMapping
    public CommuneResponseDTO createCommune(@RequestBody CommuneRequestDTO requestDTO) {
        return communeService.createCommune(requestDTO);
    }

    @GetMapping("/{communeId}")
    public CommuneResponseDTO getCommuneById(@PathVariable UUID communeId) {
        return communeService.getCommuneById(communeId);
    }
    @GetMapping("/district/{districtId}")
    public ResponseEntity<List<CommuneResponseDTO>> getCommunesByDistrict(@PathVariable UUID districtId) {
        return ResponseEntity.ok(communeService.getCommunesByDistrict(districtId));
    }

    @GetMapping
    public List<CommuneResponseDTO> getAllCommunes() {
        return communeService.getAllCommunes();
    }

    @PutMapping("/{communeId}")
    public CommuneResponseDTO updateCommune(@PathVariable UUID communeId,
                                            @RequestBody CommuneRequestDTO requestDTO) {
        return communeService.updateCommune(communeId, requestDTO);
    }

    @DeleteMapping("/{communeId}")
    public void deleteCommune(@PathVariable UUID communeId) {
        communeService.deleteCommune(communeId);
    }
}
