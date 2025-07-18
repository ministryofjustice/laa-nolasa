package com.laa.nolasa.laanolasa.entity;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;
import java.time.LocalDate;

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
    private LocalDate dob;

    @Column(name = "NI_NUMBER")
    private String niNumber;

}
