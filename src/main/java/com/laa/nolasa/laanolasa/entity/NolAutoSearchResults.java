package com.laa.nolasa.laanolasa.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private Long librId1;

    @Column(name = "LIBRA_ID_2")
    private Long librId2;

    @Column(name = "LIBRA_ID_3")
    private Long librId3;

    @Column(name = "LIBRA_ID_4")
    private Long librId4;

    @Column(name = "LIBRA_ID_5")
    private Long librId5;

    @Column(name = "LIBRA_ID_6")
    private Long librId6;

    @Column(name = "LIBRA_ID_7")
    private Long librId7;

    @Column(name = "LIBRA_ID_8")
    private Long librId8;

    @Column(name = "LIBRA_ID_9")
    private Long librId9;

    @Column(name = "LIBRA_ID_10")
    private Long librId10;

    @Column(name = "LIBRA_ID_11")
    private Long librId11;

    @Column(name = "LIBRA_ID_12")
    private Long librId12;

    @Column(name = "LIBRA_ID_13")
    private Long librId13;

    @Column(name = "LIBRA_ID_14")
    private Long librId14;

    @Column(name = "LIBRA_ID_15")
    private Long librId15;

    @OneToOne
    @JoinColumn(name="MAAT_ID")
    private RepOrders repOrders;

    @Column(name = "SEARCH_DATE", nullable = false)
    private LocalDateTime searchDate;
}
