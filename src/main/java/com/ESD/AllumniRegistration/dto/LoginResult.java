package com.ESD.AllumniRegistration.dto;

import lombok.Data;

@Data
public class LoginResult {
    private boolean success;
    private int allumniId = 0;
}
