package com.ESD.AllumniRegistration.data.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Allumni_Education")
public class AllumniEducationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "allumniId")
    private AllumniEntity allumniId;

    @Column(name = "degree", nullable = false, length = 100)
    private String degree;

    @Column(name = "institution", nullable = false, length = 100)
    private String institution;

    @Column(name = "address", length = 150)
    private String address;

    @Column(name = "joiningYear", nullable = false)
    private int joiningYear;

    @Column(name = "passingYear", nullable = false)
    private int passingYear;
}
