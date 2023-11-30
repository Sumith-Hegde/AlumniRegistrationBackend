package com.ESD.AllumniRegistration.dto;

import java.util.List;

import lombok.Data;

@Data
public class AllumniDetails {
    private String email;
    private String contactNumber;
    private List<Education> education;
    private List<AllumniOrganisationDetails> orgs;
}
