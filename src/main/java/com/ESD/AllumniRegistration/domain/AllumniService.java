package com.ESD.AllumniRegistration.domain;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ESD.AllumniRegistration.data.entities.AllumniEntity;
import com.ESD.AllumniRegistration.domain.model.Allumni;
import com.ESD.AllumniRegistration.domain.model.AllumniEducation;
import com.ESD.AllumniRegistration.domain.model.AllumniOrganisation;
import com.ESD.AllumniRegistration.domain.model.Organisation;
import com.ESD.AllumniRegistration.domain.model.Student;
import com.ESD.AllumniRegistration.dto.AllumniDetails;
import com.ESD.AllumniRegistration.dto.AllumniOrganisationDetails;
import com.ESD.AllumniRegistration.dto.Education;
import com.ESD.AllumniRegistration.dto.EducationDetails;
import com.ESD.AllumniRegistration.dto.OrgDetails;
import com.ESD.AllumniRegistration.dto.RegisterAllumni;
import com.ESD.AllumniRegistration.domain.model.Credentials;
import com.ESD.AllumniRegistration.domain.model.HR;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AllumniService {
    private final AllumniRepository allumniRepo;

    // 전체 회원 조회
    public Student fetchStudentByYearName(int gradYear, String name) {
        return allumniRepo.fetchStudentByYearName(gradYear, name);
    }

    public int checkLoginCredentials(String userName, String password) {
        AllumniEntity ent = allumniRepo.checkLoginCredentials(userName, password);
        return ent.getId();
    }

    public AllumniEducation addEducation(EducationDetails edu) {
        return allumniRepo.addEducation(mapToDomainEducationDetails(edu));
    }

    public String insertAllumniAndOrganisation(AllumniOrganisationDetails allOrgDetails) {
        // 학력 정보 수정
        Allumni allumni = allumniRepo.addAllumniDetails(mapToDomainAllumni(allOrgDetails));
        Organisation organisation = allumniRepo.addOrganisationDetails(mapToDomainOrganisation(allOrgDetails));
        AllumniOrganisation allumniOrganisation = mapToDomainAllumniOrganisation(allOrgDetails);
        allumniOrganisation.setAllumniId(allumni.getId());
        allumniOrganisation.setOrganisationId(organisation.getId());
        allumniRepo.addAllumniOrganisation(allumniOrganisation);
        return "Success";
    }

    public Organisation addOrganisation(OrgDetails org) {
        try {
            HR hr = mapToHR(org);
            AllumniOrganisation allOrg = mapToAllumniOrganisation(org);
            Organisation organisation = allumniRepo.addOrganisation(mapToOrganisation(org));
            hr.setOrganisationId(organisation.getId());
            allOrg.setOrganisationId(organisation.getId());
            allumniRepo.addHR(hr);
            allumniRepo.addAllumniOrganisation(allOrg);
            return organisation;
        } catch (Exception e) {
            return null;
        }
    }

    public AllumniDetails fetchAllumniDetails(int allumniId) {
        Allumni allumni = allumniRepo.fetchAllumni(allumniId);
        Student student = allumniRepo.fetchStudentById(allumni.getStudentId());
        List<Organisation> org = allumniRepo.fetchAllumniOrganisationsByAllummni(allumniId);
        List<AllumniEducation> edu = allumniRepo.fetchEducationsByAllummni(allumniId);
        return mapToAllumniDetails(allumni, org, edu, student);
    }

    public Allumni registerAllumni(RegisterAllumni allumniReg) {
        Allumni allumni = allumniRepo.registerAllumni(mapToDomainRegisterAllumni(allumniReg));
        allumniRepo.registerUser(mapToDomainUser(allumniReg, allumni.getId()));
        return allumni;
    }

    private Organisation mapToOrganisation(OrgDetails orgDetails) {
        Organisation org = new Organisation();
        org.setName(orgDetails.getName());
        org.setAddress(orgDetails.getAddress());
        return org;
    }

    private AllumniEducation mapToDomainEducationDetails(EducationDetails eduDetails) {
        AllumniEducation edu = new AllumniEducation();
        edu.setInstitution(eduDetails.getInstitutionName());
        edu.setAddress(eduDetails.getInstitutionAddress());
        edu.setDegree(eduDetails.getDegree());
        edu.setJoiningYear(eduDetails.getJoiningYear());
        edu.setPassingYear(eduDetails.getPassingYear());
        edu.setAllumniId(eduDetails.getAllumniId());
        return edu;
    }

    private HR mapToHR(OrgDetails orgDetails) {
        HR hr = new HR();
        hr.setFirstName(orgDetails.getFirstName());
        hr.setLastName(orgDetails.getLastName());
        hr.setEmail(orgDetails.getEmail());
        hr.setContactNumber(orgDetails.getContactNumber());
        return hr;
    }

    private AllumniOrganisation mapToAllumniOrganisation(OrgDetails orgDetails) {
        AllumniOrganisation allOrg = new AllumniOrganisation();
        allOrg.setAllumniId(orgDetails.getAllumniId());
        allOrg.setJoiningDate(orgDetails.getJoiningDate());
        allOrg.setLeavingDate(orgDetails.getLeavingDate());
        allOrg.setPosition(orgDetails.getPosition());
        return allOrg;
    }

    private Credentials mapToDomainUser(RegisterAllumni allumniReg, int id) {
        Credentials cred = new Credentials();
        cred.setUserName(allumniReg.getUserName());
        cred.setPassword(allumniReg.getPassword());
        cred.setAllumniId(id);
        return cred;
    }

    private Allumni mapToDomainRegisterAllumni(RegisterAllumni allumniReg) {
        Allumni allumni = new Allumni();
        allumni.setContactNumber(allumniReg.getContactNumber());
        allumni.setEmail(allumniReg.getEmail());
        allumni.setStudentId(allumniReg.getStudentId());
        return allumni;
    }

    private AllumniDetails mapToAllumniDetails(Allumni allumni, List<Organisation> org,
            List<AllumniEducation> edu, Student student) {
        AllumniDetails details = new AllumniDetails();
        details.setEmail(allumni.getEmail());
        details.setContactNumber(allumni.getContactNumber());
        details.setOrgs(mapToAllumniOrganisationDetails(org));
        details.setEducation(mapToEducation(edu));
        details.setFirstName(student.getFirstName());
        details.setLastName(student.getLastName());
        details.setRollNumber(student.getRollNumber());
        details.setGraduationYear(student.getGraduationYear());
        return details;
    }

    private Allumni mapToDomainAllumni(AllumniOrganisationDetails details) {
        Allumni allumni = new Allumni();
        allumni.setEmail(details.getEmail());
        allumni.setContactNumber(details.getContactNumber());
        allumni.setStudentId(details.getStudentId());
        return allumni;
    }

    public Education mapToEducation(AllumniEducation edu) {
        Education edu1 = new Education();
        edu1.setAddress(edu.getAddress());
        edu1.setInstitution(edu.getInstitution());
        edu1.setDegree(edu.getDegree());
        edu1.setJoiningYear(edu.getJoiningYear());
        edu1.setPassingYear(edu.getPassingYear());
        return edu1;
    }

    public List<Education> mapToEducation(List<AllumniEducation> edu) {
        return edu.stream().map(this::mapToEducation).toList();
    }

    public AllumniOrganisationDetails mapToAllumniOrganisationDetails(Organisation org) {
        AllumniOrganisationDetails det = new AllumniOrganisationDetails();
        det.setAddress(org.getAddress());
        det.setName(org.getName());
        return det;
    }

    public List<AllumniOrganisationDetails> mapToAllumniOrganisationDetails(List<Organisation> org) {
        return org.stream().map(this::mapToAllumniOrganisationDetails).toList();
    }

    private Organisation mapToDomainOrganisation(AllumniOrganisationDetails details) {
        Organisation org = new Organisation();
        org.setName(details.getName());
        org.setAddress(details.getAddress());
        return org;
    }

    private AllumniOrganisation mapToDomainAllumniOrganisation(AllumniOrganisationDetails details) {
        AllumniOrganisation alluminOrg = new AllumniOrganisation();
        alluminOrg.setPosition(details.getPosition());
        alluminOrg.setJoiningDate(details.getJoiningDate());
        alluminOrg.setLeavingDate(details.getLeavingDate());
        return alluminOrg;
    }
}
