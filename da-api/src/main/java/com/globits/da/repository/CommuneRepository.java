package com.globits.da.repository;

import com.globits.da.domain.Commune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommuneRepository extends JpaRepository<Commune, UUID> {
    List<Commune> findByDistrictId(UUID districtId);
}
