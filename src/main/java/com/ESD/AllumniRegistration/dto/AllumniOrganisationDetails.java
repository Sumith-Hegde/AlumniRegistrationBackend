package com.ESD.AllumniRegistration.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AllumniOrganisationDetails {

    private String name;

    private String address;

    private String position;

    private LocalDate joiningDate;

    private LocalDate leavingDate;
    private String email = "";

    private String contactNumber = "";

    private int studentId;
}
