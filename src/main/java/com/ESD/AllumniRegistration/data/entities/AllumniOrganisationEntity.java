package com.ESD.AllumniRegistration.data.entities;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Allumni_Organisation")
public class AllumniOrganisationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "organisation_id")
    private OrganisationEntity organisationId;

    @ManyToOne
    @JoinColumn(name = "allumni_id")
    private AllumniEntity allumniId;

    @Column(name = "position")
    private String position;

    @Column(name = "joining_date", nullable = false)
    private LocalDate joiningDate;

    @Column(name = "leaving_date")
    private LocalDate leavingDate;

}
