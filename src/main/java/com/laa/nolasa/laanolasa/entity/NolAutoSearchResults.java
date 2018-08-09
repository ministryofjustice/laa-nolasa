package com.laa.nolasa.laanolasa.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "XXMLA_NOL_AUTOSEARCH_RESULTS", schema = "MLA")
public class NolAutoSearchResults {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nol_search_generator")
    @SequenceGenerator(name = "nol_search_generator", sequenceName = "S_NOL_AUTOSEARCH_ID", allocationSize = 1)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "LIBRA_ID_1")
    private long librId1;

    @Column(name = "LIBRA_ID_2")
    private long librId2;

    @Column(name = "LIBRA_ID_3")
    private long librId3;

    @Column(name = "LIBRA_ID_4")
    private long librId4;

    @Column(name = "LIBRA_ID_5")
    private long librId5;

    @Column(name = "LIBRA_ID_6")
    private long librId6;

    @Column(name = "LIBRA_ID_7")
    private long librId7;

    @Column(name = "LIBRA_ID_8")
    private long librId8;

    @Column(name = "LIBRA_ID_9")
    private long librId9;

    @Column(name = "LIBRA_ID_10")
    private long librId10;

    @Column(name = "LIBRA_ID_11")
    private long librId11;

    @Column(name = "LIBRA_ID_12")
    private long librId12;

    @Column(name = "LIBRA_ID_13")
    private long librId13;

    @Column(name = "LIBRA_ID_14")
    private long librId14;

    @Column(name = "LIBRA_ID_15")
    private long librId115;

    @OneToOne
    @JoinColumn(name="MAAT_ID")
    private RepOrders repOrders;

    @Column(name = "SEARCH_DATE", nullable = false)
    private Date searchDate;
}
