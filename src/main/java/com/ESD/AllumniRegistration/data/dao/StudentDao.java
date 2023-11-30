package com.ESD.AllumniRegistration.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ESD.AllumniRegistration.data.entities.StudentEntity;

public interface StudentDao extends JpaRepository<StudentEntity, Integer> {
    StudentEntity findByGraduationYearAndFirstNameAndLastName(int year, String firstName, String lastName);
}
