package com.ESD.AllumniRegistration.domain;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ESD.AllumniRegistration.domain.model.Allumni;
import com.ESD.AllumniRegistration.domain.model.AllumniEducation;
import com.ESD.AllumniRegistration.domain.model.AllumniOrganisation;
import com.ESD.AllumniRegistration.domain.model.Organisation;
import com.ESD.AllumniRegistration.domain.model.Student;
import com.ESD.AllumniRegistration.dto.AllumniDetails;
import com.ESD.AllumniRegistration.dto.AllumniOrganisationDetails;
import com.ESD.AllumniRegistration.dto.Education;
import com.ESD.AllumniRegistration.dto.RegisterAllumni;
import com.ESD.AllumniRegistration.domain.model.Credentials;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AllumniService {
    private final AllumniRepository allumniRepo;

    // 전체 회원 조회
    public Student fetchStudentByYearName(int gradYear, String name) {
        return allumniRepo.fetchStudentByYearName(gradYear, name);
    }

    public boolean checkLoginCredentials(String userName, String password) {
        return allumniRepo.checkLoginCredentials(userName, password);
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

    public AllumniDetails fetchAllumniDetails(int allumniId) {
        Allumni allumni = allumniRepo.fetchAllumni(allumniId);
        List<Organisation> org = allumniRepo.fetchAllumniOrganisationsByAllummni(allumniId);
        List<AllumniEducation> edu = allumniRepo.fetchEducationsByAllummni(allumniId);
        return mapToAllumniDetails(allumni, org, edu);
    }

    public Allumni registerAllumni(RegisterAllumni allumniReg) {
        Allumni allumni = allumniRepo.registerAllumni(mapToDomainRegisterAllumni(allumniReg));
        allumniRepo.registerUser(mapToDomainUser(allumniReg, allumni.getId()));
        return allumni;
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
            List<AllumniEducation> edu) {
        AllumniDetails details = new AllumniDetails();
        details.setEmail(allumni.getEmail());
        details.setContactNumber(allumni.getContactNumber());
        details.setOrgs(mapToAllumniOrganisationDetails(org));
        details.setEducation(mapToEducation(edu));
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
