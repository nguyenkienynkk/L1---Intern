package com.globits.da.domain;

import com.globits.core.domain.BaseObject;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_province")
public class Province extends BaseObject {
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
    private List<District> districts;

}
