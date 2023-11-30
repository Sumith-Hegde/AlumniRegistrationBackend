package com.ESD.AllumniRegistration.data.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "OrganisationHRs")
public class HREntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "contact_number", length = 15)
    private String contactNumber;

    @ManyToOne
    @JoinColumn(name = "organisation_id")
    private OrganisationEntity organisationId;

}
