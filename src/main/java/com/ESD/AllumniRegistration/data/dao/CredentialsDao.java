package com.ESD.AllumniRegistration.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ESD.AllumniRegistration.data.entities.CredentialsEntity;

public interface CredentialsDao extends JpaRepository<CredentialsEntity, Integer> {
    CredentialsEntity findByUserName(String userName);
}
