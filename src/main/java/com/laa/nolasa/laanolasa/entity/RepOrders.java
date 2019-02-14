package com.laa.nolasa.laanolasa.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "REP_ORDERS", schema = "TOGDATA")
public class RepOrders {

    @Id
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "HEARING_DATE")
    private LocalDateTime hearingDate;

    @Column(name = "ARREST_SUMMONS_NO")
    private String arrestSummonsNo;

    @ManyToOne
    @JoinColumn(name = "APPL_ID")
    private Applicants applicants;

    @ManyToOne
    @JoinColumn(name = "MACO_COURT")
    private MagistrateCourts magistrateCourts;

    @ManyToOne
    @JoinColumn(name = "CMU_ID")
    private CaseManagementUnits caseManagementUnits;

    @OneToOne(mappedBy = "repOrders", cascade={CascadeType.ALL})
    private Nol nol;
}
