package com.globits.da.domain;

import com.globits.core.domain.BaseObject;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_district")
public class District extends BaseObject {
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;
    @OneToMany(mappedBy = "district", cascade = CascadeType.REMOVE)
    private List<Commune> communes;

}
