package com.laa.nolasa.laanolasa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Data
@Table(name = "APPLICANTS", schema = "TOGDATA")
public class Applicants {
    @Id
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "DOB")
    private Date dob;

    @Column(name = "NI_NUMBER")
    private String niNumber;

}
