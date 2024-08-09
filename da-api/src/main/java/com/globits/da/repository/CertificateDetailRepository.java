package com.globits.da.repository;

import com.globits.da.domain.TblCertificateDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CertificateDetailRepository extends JpaRepository<TblCertificateDetail, UUID> {

    boolean existsByEmployeeIdAndCertificateIdAndProvinceIdAndActive(UUID employeeId, UUID certificateId, UUID provinceId, Boolean active);

    @Query("SELECT COUNT(cd) FROM TblCertificateDetail cd " +
            "WHERE cd.employee.id = :employeeId " +
            "AND cd.active = TRUE " +
            "AND cd.startDay <= CURRENT_DATE " +
            "AND cd.endDay >= CURRENT_DATE")
    long countValidCertificatesByEmployeeId(@Param("employeeId") UUID employeeId);

    @Query("SELECT COUNT(cd) FROM TblCertificateDetail cd WHERE cd.province.id = :provinceId AND cd.active = TRUE")
    long countActiveCertificatesByProvinceId(@Param("provinceId") UUID provinceId);
}
