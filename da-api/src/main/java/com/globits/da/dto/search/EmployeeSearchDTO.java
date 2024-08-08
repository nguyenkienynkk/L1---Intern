package com.globits.da.dto.search;

import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeSearchDTO {
    private int id;
    private int pageIndex;
    private int pageSize;
    private String keyword;
}
