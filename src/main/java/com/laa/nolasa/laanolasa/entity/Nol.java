package com.laa.nolasa.laanolasa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "XXMLA_NOL", schema = "MLA")
public class Nol {

    @Id
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MAAT_ID")
    private RepOrders repOrders;

    @Column(name = "DATE_ADDED", nullable = false)
    private Date dateAdded;

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

}
