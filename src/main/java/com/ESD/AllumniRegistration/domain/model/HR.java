package com.ESD.AllumniRegistration.domain.model;

import lombok.Data;

@Data
public class HR {
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String contactNumber;

    private int organisationId;
}
