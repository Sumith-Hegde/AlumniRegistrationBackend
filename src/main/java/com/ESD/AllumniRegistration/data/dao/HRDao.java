package com.ESD.AllumniRegistration.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ESD.AllumniRegistration.data.entities.HREntity;

public interface HRDao extends JpaRepository<HREntity, Integer> {

}
