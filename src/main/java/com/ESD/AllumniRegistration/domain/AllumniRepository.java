package com.ESD.AllumniRegistration.domain;

import java.util.List;

import com.ESD.AllumniRegistration.domain.model.Allumni;
import com.ESD.AllumniRegistration.domain.model.AllumniEducation;
import com.ESD.AllumniRegistration.domain.model.AllumniOrganisation;
import com.ESD.AllumniRegistration.domain.model.Credentials;
import com.ESD.AllumniRegistration.domain.model.Organisation;
import com.ESD.AllumniRegistration.domain.model.Student;
import com.ESD.AllumniRegistration.dto.RegisterAllumni;

public interface AllumniRepository {
    public Student fetchStudentByYearName(int gradYear, String name);

    public Allumni addAllumniDetails(Allumni allumni);

    public Organisation addOrganisationDetails(Organisation allumniOrg);

    public AllumniOrganisation addAllumniOrganisation(AllumniOrganisation allumniOrganisation);

    public boolean checkLoginCredentials(String userName, String password);

    public Allumni fetchAllumni(int allumniId);

    // public List<Organisation> fetchOrganisationsByAllummni(int allumniId);

    public List<AllumniEducation> fetchEducationsByAllummni(int allumniId);

    public List<Organisation> fetchAllumniOrganisationsByAllummni(int allumniId);

    public Allumni registerAllumni(Allumni allumni);

    public Credentials registerUser(Credentials cred);

}
