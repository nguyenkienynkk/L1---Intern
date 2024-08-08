package com.globits.da.repository;

import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("select e from Employee e where (" +
            ":keyword is null " +
            "or e.name like %:keyword% " +
            "or e.email like %:keyword% " +
            "or e.code like %:keyword%)")
    List<Employee> searchEmployees(@Param("keyword") String keyword);

    @Query("select new com.globits.da.dto.EmployeeDTO(em) from Employee em")
    Set<EmployeeDTO> getAll();
}
