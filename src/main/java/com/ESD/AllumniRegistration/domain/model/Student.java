package com.ESD.AllumniRegistration.domain.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Student {
    private int id;

    private int rollNumber;

    private String firstName;

    private String lastName;

    private String email;

    private String photo;

    private BigDecimal cgpa = new BigDecimal(0.0);

    private int totalCredits;

    private int graduationYear;

    private String specialization;
}
