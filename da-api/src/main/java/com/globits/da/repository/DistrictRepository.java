package com.globits.da.repository;

import com.globits.da.domain.District;
import com.globits.da.domain.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface DistrictRepository extends JpaRepository<District, UUID> {
    List<District> findByProvinceId(UUID provinceId);
    Optional<District> findByNameAndProvince(String name, Province province);
}
