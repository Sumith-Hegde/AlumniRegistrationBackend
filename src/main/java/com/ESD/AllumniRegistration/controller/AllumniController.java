package com.ESD.AllumniRegistration.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ESD.AllumniRegistration.domain.AllumniService;
import com.ESD.AllumniRegistration.domain.model.Allumni;
import com.ESD.AllumniRegistration.domain.model.Student;
import com.ESD.AllumniRegistration.dto.AllumniDetails;
import com.ESD.AllumniRegistration.dto.AllumniOrganisationDetails;
import com.ESD.AllumniRegistration.dto.LoginCredentials;
import com.ESD.AllumniRegistration.dto.LoginResult;
import com.ESD.AllumniRegistration.dto.RegisterAllumni;
import com.ESD.AllumniRegistration.dto.RequestAllumni;
import com.ESD.AllumniRegistration.dto.RequestStudent;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/allumni")
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*localhost*")
public class AllumniController {
    private final AllumniService allumniService;

    @PostMapping("/studentDetails")
    public Student fetchStudentByYearName(@RequestBody RequestStudent student) {
        return allumniService.fetchStudentByYearName(student.getGradYear(), student.getName());
    }

    @PostMapping("/addDetails")
    public String addAllumniAndOrganisationDetails(@RequestBody AllumniOrganisationDetails details) {
        return allumniService.insertAllumniAndOrganisation(details);
    }

    @PostMapping("/loginCredentials")
    public LoginResult checkLoginCredentails(@RequestBody LoginCredentials credentials) {
        LoginResult loginResult = new LoginResult();
        if (allumniService.checkLoginCredentials(credentials.getUserName(), credentials.getPassword())) {
            loginResult.setSuccess(true);
        } else {
            loginResult.setSuccess(false);
        }
        return loginResult;
    }

    @PostMapping("/allumniDetails")
    public AllumniDetails fetchAllumniDetails(@RequestBody RequestAllumni allumni) {
        AllumniDetails details = allumniService.fetchAllumniDetails(allumni.getAllumniId());
        return details;
    }

    @PostMapping("/registerAllumni")
    public Allumni registerAllumni(@RequestBody RegisterAllumni allumniReg) {
        Allumni allumni = allumniService.registerAllumni(allumniReg);
        return allumni;
    }
}
