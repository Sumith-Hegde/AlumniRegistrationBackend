package com.ESD.AllumniRegistration.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class OrgDetails {
    private String name;
    private String address;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
    private int allumniId;
    private String position;
    private LocalDate joiningDate;
    private LocalDate leavingDate;
}
