package com.laa.nolasa.laanolasa.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity

@Table(name = "XXMLA_NOL_AUTOSEARCH_RESULTS", schema = "MLA")
@Slf4j
public class NolAutoSearchResults {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nol_search_generator")
    @SequenceGenerator(name = "nol_search_generator", sequenceName = "S_NOL_AUTOSEARCH_ID", allocationSize = 1)
    @Column(name = "ID", updatable = false, nullable = false)
    @Getter@Setter
    private Long id;

    @Column(name = "LIBRA_ID_1")
    private Long libraId1;

    @Column(name = "LIBRA_ID_2")
    private Long libraId2;

    @Column(name = "LIBRA_ID_3")
    private Long libraId3;

    @Column(name = "LIBRA_ID_4")
    private Long libraId4;

    @Column(name = "LIBRA_ID_5")
    private Long libraId5;

    @Column(name = "LIBRA_ID_6")
    private Long libraId6;

    @Column(name = "LIBRA_ID_7")
    private Long libraId7;

    @Column(name = "LIBRA_ID_8")
    private Long libraId8;

    @Column(name = "LIBRA_ID_9")
    private Long libraId9;

    @Column(name = "LIBRA_ID_10")
    private Long libraId10;

    @Column(name = "LIBRA_ID_11")
    private Long libraId11;

    @Column(name = "LIBRA_ID_12")
    private Long libraId12;

    @Column(name = "LIBRA_ID_13")
    private Long libraId13;

    @Column(name = "LIBRA_ID_14")
    private Long libraId14;

    @Column(name = "LIBRA_ID_15")
    private Long libraId15;

    @OneToOne
    @JoinColumn(name="MAAT_ID")
    @Getter@Setter
    private RepOrders repOrders;

    @Column(name = "SEARCH_DATE", nullable = false)
    @Getter@Setter
    private LocalDateTime searchDate;

    public List<Long> getLibraIds() {
        List<Long> libraIds = new ArrayList<>();

        for (int i = 1; i<= 15; i++) {
            try {
                Field libraIdField = this.getClass().getDeclaredField("libraId" + i);
                libraIdField.setAccessible(true);
                Long libraId = (Long)libraIdField.get(this);
                if (libraId != null ) {
                    libraIds.add(libraId);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                log.error("Failed to map", e);
                throw new RuntimeException(e);
            }
        }
        return libraIds;
    }

    public void setLibraIds(List<Long> libraIds)  {

        int i = 1;
        for (Long libraId : libraIds) {
            try {
                Field libraIdField = this.getClass().getDeclaredField("libraId" + i++);
                libraIdField.setAccessible(true);
                libraIdField.set(this, libraId);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                log.error("Failed to map", e);
                throw new RuntimeException(e);
            }
        }
    }
}
