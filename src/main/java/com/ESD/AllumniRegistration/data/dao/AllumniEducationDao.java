package com.ESD.AllumniRegistration.data.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ESD.AllumniRegistration.data.entities.AllumniEducationEntity;

public interface AllumniEducationDao extends JpaRepository<AllumniEducationEntity, Integer> {
    List<AllumniEducationEntity> findAllByAllumniIdId(int allumniID);
}
