package com.ESD.AllumniRegistration.data.entities;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Student")

public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rollNumber")
    private int rollNumber;

    @Column(name = "firstName", nullable = false, length = 50)
    private String firstName;

    @Column(name = "lastName", length = 50)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "photo", unique = true, length = 300)
    private String photo;

    @Column(name = "cgpa", precision = 4, scale = 2, nullable = false)
    private BigDecimal cgpa = new BigDecimal(0.0);

    @Column(name = "totalCredits", nullable = false)
    private int totalCredits;

    @Column(name = "graduationYear", nullable = false)
    private int graduationYear;

    @Column(name = "specialization", length = 50)
    private String specialization;
}
