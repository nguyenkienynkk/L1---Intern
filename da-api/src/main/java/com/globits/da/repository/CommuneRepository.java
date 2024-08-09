package com.globits.da.repository;

import com.globits.da.domain.Commune;
import com.globits.da.domain.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommuneRepository extends JpaRepository<Commune, UUID> {
    List<Commune> findByDistrictId(UUID districtId);
    Optional<Commune> findByNameAndDistrict(String name, District district);
}
