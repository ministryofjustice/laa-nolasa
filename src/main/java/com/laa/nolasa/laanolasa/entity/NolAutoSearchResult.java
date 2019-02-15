package com.laa.nolasa.laanolasa.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Data
@Table(name = "XXMLA_NOL_AUTOSEARCH_RESULT", schema = "MLA")
@Slf4j
public class NolAutoSearchResult {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nol_search_generator")
    @SequenceGenerator(name = "nol_search_generator", sequenceName = "S_NOL_AUTOSEARCH_ID", allocationSize = 1)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "LIBRA_ID")
    private Long libraId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NOL_ID", referencedColumnName = "id")
    private Nol nol;

    public NolAutoSearchResult(Long libraId, Nol nol) {
        this.libraId = libraId;
        this.nol = nol;
    }
}
