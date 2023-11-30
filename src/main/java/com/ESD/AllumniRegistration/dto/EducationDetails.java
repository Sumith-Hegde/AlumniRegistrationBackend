package com.ESD.AllumniRegistration.dto;

import lombok.Data;

@Data
public class EducationDetails {
    private String degree;
    private int joiningYear;
    private int passingYear;
    private String institutionName;
    private String institutionAddress;
    private int allumniId;
}
