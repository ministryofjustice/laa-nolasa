package com.laa.nolasa.laanolasa.entity;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

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
