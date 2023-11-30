package com.ESD.AllumniRegistration.data.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ESD.AllumniRegistration.data.entities.AllumniOrganisationEntity;

public interface AllumniOrganisationDao extends JpaRepository<AllumniOrganisationEntity, Integer> {
    List<AllumniOrganisationEntity> findAllByAllumniIdId(int allumniId);
}
