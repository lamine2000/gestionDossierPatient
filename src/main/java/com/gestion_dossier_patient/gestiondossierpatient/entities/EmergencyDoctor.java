package com.gestion_dossier_patient.gestiondossierpatient.entities;

import com.gestion_dossier_patient.gestiondossierpatient.security.AuthoritiesConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "emergency_doctor")
public class EmergencyDoctor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private User user;

    private static final String role = AuthoritiesConstants.EMERGENCY_DOCTOR;
}
