package com.ESD.AllumniRegistration.dto;

import lombok.Data;

@Data
public class RegisterAllumni {
    private String email;
    private String contactNumber;
    private String userName;
    private String password;
    private int studentId;
}
