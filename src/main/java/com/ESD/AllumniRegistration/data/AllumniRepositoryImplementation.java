package com.ESD.AllumniRegistration.data;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.ESD.AllumniRegistration.data.dao.AllummniDao;
import com.ESD.AllumniRegistration.data.dao.AllumniEducationDao;
import com.ESD.AllumniRegistration.data.dao.AllumniOrganisationDao;
import com.ESD.AllumniRegistration.data.dao.CredentialsDao;
import com.ESD.AllumniRegistration.data.dao.HRDao;
import com.ESD.AllumniRegistration.data.dao.OrganisationDao;
import com.ESD.AllumniRegistration.data.dao.StudentDao;
import com.ESD.AllumniRegistration.data.entities.AllumniEducationEntity;
import com.ESD.AllumniRegistration.data.entities.AllumniEntity;
import com.ESD.AllumniRegistration.data.entities.AllumniOrganisationEntity;
import com.ESD.AllumniRegistration.data.entities.CredentialsEntity;
import com.ESD.AllumniRegistration.data.entities.HREntity;
import com.ESD.AllumniRegistration.data.entities.OrganisationEntity;
import com.ESD.AllumniRegistration.data.entities.StudentEntity;
import com.ESD.AllumniRegistration.domain.AllumniRepository;
import com.ESD.AllumniRegistration.domain.model.Allumni;
import com.ESD.AllumniRegistration.domain.model.AllumniEducation;
import com.ESD.AllumniRegistration.domain.model.AllumniOrganisation;
import com.ESD.AllumniRegistration.domain.model.Credentials;
import com.ESD.AllumniRegistration.domain.model.HR;
import com.ESD.AllumniRegistration.domain.model.Organisation;
import com.ESD.AllumniRegistration.domain.model.Student;
import com.ESD.AllumniRegistration.dto.AllumniDetails;
import com.ESD.AllumniRegistration.dto.Education;
import com.ESD.AllumniRegistration.dto.RegisterAllumni;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AllumniRepositoryImplementation implements AllumniRepository {
    private final StudentDao studentDao;
    private final AllummniDao allumniDao;
    private final OrganisationDao orgDao;
    private final AllumniOrganisationDao allOrgDao;
    private final CredentialsDao credentialsDao;
    private final AllumniEducationDao eduDao;
    private final AllumniOrganisationDao allumniOrgDao;
    private final HRDao hrDao;

    @Override
    public Student fetchStudentByYearName(int gradYear, String name) {
        String[] words = name.split(" ");
        StudentEntity entity = studentDao.findByGraduationYearAndFirstNameAndLastName(gradYear, words[0], words[1]);
        return mapToDomainStudent(entity);
    }

    @Override
    public Allumni addAllumniDetails(Allumni allumni) {
        AllumniEntity entity = allumniDao.save(mapToEntityAllumniEntity(allumni));
        return mapToDomainAllumni(entity);
    }

    @Override
    public AllumniOrganisation addAllumniOrganisation(AllumniOrganisation allumniOrganisation) {
        AllumniOrganisationEntity entity = allOrgDao.save(mapToEntityAllumniOrganisationEntity(allumniOrganisation));
        return mapToDomainAllumniOrganisation(entity);
    }

    @Override
    public HR addHR(HR hr) {
        try {
            HREntity entity = hrDao.save(mapToEntityHREntity(hr));
            return mapToDomainHR(entity);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Organisation addOrganisation(Organisation org) {
        try {
            OrganisationEntity o = orgDao.save(mapToEntityOrganisationEntity(org));
            return mapToDomainOrganisation(o);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public AllumniEducation addEducation(AllumniEducation edu) {
        try {
            AllumniEducationEntity edu1 = eduDao.save(mapToEntityEducationEntity(edu));
            return mapToDomainEducation(edu1);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public AllumniEntity checkLoginCredentials(String userName, String password) {
        CredentialsEntity entity = credentialsDao.findByUserName(userName);
        if (password.equals(entity.getPassword())) {
            return entity.getAllumniId();
        }
        return null;
    }

    @Override
    public Student fetchStudentById(int id) {
        try {
            Student stu = mapToDomainStudent(studentDao.findById(id).get());
            return stu;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Organisation addOrganisationDetails(Organisation org) {
        OrganisationEntity entity = orgDao.save(mapToEntityOrganisationEntity(org));
        return mapToDomainOrganisation(entity);
    }

    @Override
    public Allumni fetchAllumni(int allumniId) {
        Allumni allumni = mapToDomainAllumni(allumniDao.findById(allumniId).get());
        return allumni;
    }

    @Override
    public List<Organisation> fetchAllumniOrganisationsByAllummni(int allumniId) {
        List<AllumniOrganisationEntity> allOrg = allumniOrgDao.findAllByAllumniIdId(allumniId);
        List<Organisation> org = new ArrayList<>();
        for (AllumniOrganisationEntity allOrgEnt : allOrg) {
            org.add(mapToDomainOrganisation(allOrgEnt.getOrganisationId()));
        }
        return org;
    }

    @Override
    public List<AllumniEducation> fetchEducationsByAllummni(int allumniId) {
        List<AllumniEducation> edu = mapToDomainEducations(eduDao.findAllByAllumniIdId(allumniId));
        return edu;
    }

    @Override
    public Allumni registerAllumni(Allumni allumni) {
        return mapToDomainAllumni(allumniDao.save(mapToEntityAllumniEntity(allumni)));
    }

    @Override
    public Credentials registerUser(Credentials cred) {
        return mapToDomainCredentialsEntity(credentialsDao.save(mapToEntityCredentialsEntity(cred)));
    }

    private AllumniOrganisation mapToDomainAllumniOrganisation(AllumniOrganisationEntity allumniOrg) {
        AllumniOrganisation domainAllumniOrg = new AllumniOrganisation();
        domainAllumniOrg.setId(allumniOrg.getId());
        domainAllumniOrg.setAllumniId(allumniOrg.getAllumniId().getId());
        domainAllumniOrg.setOrganisationId(allumniOrg.getOrganisationId().getId());
        domainAllumniOrg.setJoiningDate(allumniOrg.getJoiningDate());
        domainAllumniOrg.setLeavingDate(allumniOrg.getLeavingDate());
        domainAllumniOrg.setPosition(allumniOrg.getPosition());
        return domainAllumniOrg;
    }

    private List<AllumniOrganisation> mapToDomainAllumniOrganisations(List<AllumniOrganisationEntity> entities) {
        return entities.stream().map(this::mapToDomainAllumniOrganisation).toList();
    }

    private Allumni mapToDomainAllumni(AllumniEntity allumni) {
        Allumni domainAllumni = new Allumni();
        domainAllumni.setId(allumni.getId());
        domainAllumni.setStudentId(allumni.getStudentId().getId());
        domainAllumni.setEmail(allumni.getEmail());
        domainAllumni.setContactNumber(allumni.getContactNumber());
        return domainAllumni;
    }

    private AllumniEducation mapToDomainEducation(AllumniEducationEntity allumniEdu) {
        AllumniEducation domainEdu = new AllumniEducation();
        domainEdu.setAllumniId(allumniEdu.getAllumniId().getId());
        domainEdu.setDegree(allumniEdu.getDegree());
        domainEdu.setId(allumniEdu.getId());
        domainEdu.setInstitution(allumniEdu.getInstitution());
        domainEdu.setJoiningYear(allumniEdu.getJoiningYear());
        domainEdu.setPassingYear(allumniEdu.getPassingYear());
        domainEdu.setAddress(allumniEdu.getAddress());
        return domainEdu;
    }

    private List<AllumniEducation> mapToDomainEducations(List<AllumniEducationEntity> entities) {
        return entities.stream().map(this::mapToDomainEducation).toList();
    }

    private HR mapToDomainHR(HREntity hr) {
        HR domainHr = new HR();
        domainHr.setFirstName(hr.getFirstName());
        domainHr.setLastName(hr.getLastName());
        domainHr.setEmail(hr.getEmail());
        domainHr.setOrganisationId(hr.getOrganisationId().getId());
        domainHr.setContactNumber(hr.getContactNumber());
        domainHr.setId(hr.getId());
        return domainHr;
    }

    private Organisation mapToDomainOrganisation(OrganisationEntity org) {
        Organisation domainOrg = new Organisation();
        domainOrg.setId(org.getId());
        domainOrg.setName(org.getName());
        domainOrg.setAddress(org.getAddress());
        return domainOrg;
    }

    private List<Organisation> mapToDomainOrganisations(List<OrganisationEntity> entities) {
        return entities.stream().map(this::mapToDomainOrganisation).toList();
    }

    private Student mapToDomainStudent(StudentEntity student) {
        Student domainStudnet = new Student();
        domainStudnet.setFirstName(student.getFirstName());
        domainStudnet.setLastName(student.getLastName());
        domainStudnet.setId(student.getId());
        domainStudnet.setCgpa(student.getCgpa());
        domainStudnet.setEmail(student.getEmail());
        domainStudnet.setTotalCredits(student.getTotalCredits());
        domainStudnet.setPhoto(student.getPhoto());
        domainStudnet.setGraduationYear(student.getGraduationYear());
        domainStudnet.setRollNumber(student.getRollNumber());
        domainStudnet.setSpecialization(student.getSpecialization());
        return domainStudnet;
    }

    private Credentials mapToDomainCredentialsEntity(CredentialsEntity credentials) {
        Credentials creds = new Credentials();
        creds.setUserName(credentials.getUserName());
        creds.setPassword(credentials.getPassword());
        return creds;
    }

    private AllumniOrganisationEntity mapToEntityAllumniOrganisationEntity(AllumniOrganisation allumniOrg) {
        AllumniOrganisationEntity orgEntity = new AllumniOrganisationEntity();
        orgEntity.setAllumniId(allumniDao.findById(allumniOrg.getAllumniId()).get());
        orgEntity.setOrganisationId(orgDao.findById(allumniOrg.getOrganisationId()).get());
        orgEntity.setJoiningDate(allumniOrg.getJoiningDate());
        orgEntity.setLeavingDate(allumniOrg.getLeavingDate());
        orgEntity.setPosition(allumniOrg.getPosition());
        return orgEntity;
    }

    private AllumniEntity mapToEntityAllumniEntity(Allumni allumni) {
        AllumniEntity allumniEntity = new AllumniEntity();
        allumniEntity.setEmail(allumni.getEmail());
        allumniEntity.setStudentId(studentDao.findById(allumni.getStudentId()).get());
        allumniEntity.setContactNumber(allumni.getContactNumber());
        return allumniEntity;
    }

    private AllumniEducationEntity mapToEntityEducationEntity(AllumniEducation allumniEdu) {
        AllumniEducationEntity eduEntity = new AllumniEducationEntity();
        eduEntity.setId(allumniEdu.getId());
        eduEntity.setDegree(allumniEdu.getDegree());
        eduEntity.setInstitution(allumniEdu.getInstitution());
        eduEntity.setAllumniId(allumniDao.findById(allumniEdu.getAllumniId()).get());
        eduEntity.setAddress(allumniEdu.getAddress());
        eduEntity.setJoiningYear(allumniEdu.getJoiningYear());
        eduEntity.setPassingYear(allumniEdu.getPassingYear());
        return eduEntity;
    }

    private HREntity mapToEntityHREntity(HR hr) {
        HREntity hrEntity = new HREntity();
        hrEntity.setFirstName(hr.getFirstName());
        hrEntity.setLastName(hr.getLastName());
        hrEntity.setId(hr.getId());
        hrEntity.setEmail(hr.getEmail());
        hrEntity.setContactNumber(hr.getContactNumber());
        hrEntity.setOrganisationId(orgDao.findById(hr.getOrganisationId()).get());
        return hrEntity;
    }

    private OrganisationEntity mapToEntityOrganisationEntity(Organisation org) {
        OrganisationEntity orgEntity = new OrganisationEntity();
        orgEntity.setName(org.getName());
        orgEntity.setAddress(org.getAddress());
        return orgEntity;
    }

    private StudentEntity mapToEntityStudentEntity(Student student) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setFirstName(student.getFirstName());
        studentEntity.setLastName(student.getLastName());
        studentEntity.setId(student.getId());
        studentEntity.setCgpa(student.getCgpa());
        studentEntity.setEmail(student.getEmail());
        studentEntity.setGraduationYear(student.getGraduationYear());
        studentEntity.setPhoto(student.getPhoto());
        studentEntity.setRollNumber(student.getRollNumber());
        studentEntity.setSpecialization(student.getSpecialization());
        studentEntity.setTotalCredits(student.getTotalCredits());
        return studentEntity;
    }

    private CredentialsEntity mapToEntityCredentialsEntity(Credentials credentials) {
        CredentialsEntity credsEntity = new CredentialsEntity();
        credsEntity.setUserName(credentials.getUserName());
        credsEntity.setPassword(credentials.getPassword());
        credsEntity.setAllumniId(allumniDao.findById(credentials.getAllumniId()).get());
        return credsEntity;
    }
}
