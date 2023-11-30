package com.ESD.AllumniRegistration.domain.model;

import lombok.Data;

@Data
public class Credentials {
    private int id;
    private String userName;
    private String password;
    private int allumniId;

}
