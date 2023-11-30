package com.ESD.AllumniRegistration.domain.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AllumniOrganisation {
    private int id;

    private int organisationId;

    private int allumniId;

    private String position;

    private LocalDate joiningDate;

    private LocalDate leavingDate;

}
