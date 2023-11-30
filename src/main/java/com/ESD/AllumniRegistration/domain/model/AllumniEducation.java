package com.ESD.AllumniRegistration.domain.model;

import lombok.Data;

@Data
public class AllumniEducation {
    private int id;

    private String degree;

    private String institution;

    private String address;

    private int joiningYear;

    private int passingYear;

    private int allumniId;
}
