package com.globits.da.domain;

import com.globits.core.domain.BaseObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_commune")
public class Commune extends BaseObject {
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;
}
