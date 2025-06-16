package com.laa.nolasa.laanolasa.entity;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Data
@Table(name = "MAGISTRATE_COURTS", schema = "TOGDATA")
public class MagistrateCourts {

    @Id
    @Column(name = "COURT", updatable = false, nullable = false)
    private String court;

    @Column(name = "CJS_AREA_CODE", updatable = false, nullable = false)
    private String cjsAreaCode;

}
