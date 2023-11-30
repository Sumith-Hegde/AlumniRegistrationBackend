package com.ESD.AllumniRegistration.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ESD.AllumniRegistration.domain.AllumniService;
import com.ESD.AllumniRegistration.domain.model.Allumni;
import com.ESD.AllumniRegistration.domain.model.AllumniEducation;
import com.ESD.AllumniRegistration.domain.model.Organisation;
import com.ESD.AllumniRegistration.domain.model.Student;
import com.ESD.AllumniRegistration.dto.AllumniDetails;
import com.ESD.AllumniRegistration.dto.AllumniOrganisationDetails;
import com.ESD.AllumniRegistration.dto.Education;
import com.ESD.AllumniRegistration.dto.EducationDetails;
import com.ESD.AllumniRegistration.dto.LoginCredentials;
import com.ESD.AllumniRegistration.dto.LoginResult;
import com.ESD.AllumniRegistration.dto.OrgDetails;
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
        int allumniId = allumniService.checkLoginCredentials(credentials.getUserName(), credentials.getPassword());
        if (allumniId != 0) {
            loginResult.setSuccess(true);
            loginResult.setAllumniId(allumniId);
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
        System.out.println(allumniReg);
        Allumni allumni = allumniService.registerAllumni(allumniReg);
        return allumni;
    }

    @PostMapping("/addOrganisation")
    public Organisation addOrganisation(@RequestBody OrgDetails org) {
        try {
            Organisation organisation = allumniService.addOrganisation(org);
            return organisation;
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/addEducation")
    public AllumniEducation addEducation(@RequestBody EducationDetails eduDetails) {
        try {
            AllumniEducation edu = allumniService.addEducation(eduDetails);
            return edu;
        } catch (Exception e) {
            return null;
        }
    }
}
