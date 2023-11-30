package com.ESD.AllumniRegistration.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ESD.AllumniRegistration.data.entities.OrganisationEntity;

public interface OrganisationDao extends JpaRepository<OrganisationEntity, Integer> {
    // List<OrganisationEntity> findByAllumniId(int allumniId);

    // @Query("Select name,address from Organisations where id IN :ids")
    // List<OrganisationEntity> findAllById1(@Param("ids") List<Integer> ids);
}
