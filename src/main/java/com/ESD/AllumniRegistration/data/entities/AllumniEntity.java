package com.ESD.AllumniRegistration.data.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Allumni")
public class AllumniEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email", length = 100, unique = true, nullable = false)
    private String email;

    @Column(name = "contact_number", length = 15, unique = true, nullable = false)
    private String contactNumber;

    @OneToOne
    @JoinColumn(name = "student_id")
    private StudentEntity studentId;
}
