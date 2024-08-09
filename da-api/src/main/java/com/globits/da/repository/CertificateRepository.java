package com.globits.da.repository;

import com.globits.da.domain.TblCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CertificateRepository extends JpaRepository<TblCertificate, UUID> {
    boolean existsByName(String name);
}
