package com.laa.nolasa.laanolasa.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Data
@Table(name = "CASE_MANAGEMENT_UNITS", schema = "TOGDATA")
public class CaseManagementUnits {

    @Id
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "CMU_NAME", nullable = false)
    private String cmuName;
}
