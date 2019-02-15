package com.laa.nolasa.laanolasa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "XXMLA_NOL", schema = "MLA")
public class Nol {

    @Id
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "MAAT_ID")
    private RepOrders repOrders;

    @Column(name = "DATE_ADDED", nullable = false)
    private LocalDateTime dateAdded;

    @Column(name = "CASEWORKER_ADDED", nullable = false)
    private String caseWorkerAdded;

    @Column(name = "DATE_LAST_CHECKED")
    private LocalDateTime dateLastChecked;

    @Column(name = "CASEWORKER_LAST_CHECKED")
    private String caseWorkerLastChecked;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "DATE_LAST_MODIFIED")
    private LocalDateTime dateLastModified;

    @Column(name = "USER_LAST_MODIFIED")
    private String userLastModified;

    @Column(name = "LETTER_SENT_DATE")
    private LocalDateTime letterSentDate;

    @Column(name = "LETTER_SENT_CASEWORKER")
    private String letterSentCaseWorker;

    @Column(name = "REMOVED_DATE")
    private LocalDateTime removedDate;

    @Column(name = "REMOVED_CASEWORKER")
    private String removedCaseWorker;

    @Column(name = "AUTO_SEARCH_DATE")
    private LocalDateTime autoSearchDate;

    @OneToMany(mappedBy = "nol",fetch=FetchType.LAZY,cascade=CascadeType.ALL, targetEntity = NolAutoSearchResult.class, orphanRemoval=true)
    private List<NolAutoSearchResult> autoSearchResults = new ArrayList<>();

}
