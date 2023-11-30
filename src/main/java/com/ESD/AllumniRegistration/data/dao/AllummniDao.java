package com.ESD.AllumniRegistration.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ESD.AllumniRegistration.data.entities.AllumniEntity;

public interface AllummniDao extends JpaRepository<AllumniEntity, Integer> {

}
