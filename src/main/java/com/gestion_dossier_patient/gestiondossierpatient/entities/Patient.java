package com.gestion_dossier_patient.gestiondossierpatient.entities;

import com.gestion_dossier_patient.gestiondossierpatient.security.AuthoritiesConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patient")
public class Patient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private User user;

    @ManyToOne
    private Insurance insurance;

    private String cniTutor;

    private int height;

    private float weight;

    private String emergencyNumber;

    private String bloodGroup;

    private String allergies;

    private String chronicDiseases;

    private String surgeries;

    private String familyHistory;

    private String socialHistory;

    private String medicalHistory;

    private String currentMedication;

    private static final String role = AuthoritiesConstants.PATIENT;
}
