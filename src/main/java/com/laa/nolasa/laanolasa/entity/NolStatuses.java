package com.laa.nolasa.laanolasa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "XXMLA_NOL_STATUSES", schema = "MLA")
public class NolStatuses {

    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Id
    @Column(name = "DESCRIPTION", updatable = false, nullable = false)
    private String description;

    @Column(name = "EED")
    private Date eed;

    @Column(name = "ESD", nullable = false)
    private Date esd;
}
