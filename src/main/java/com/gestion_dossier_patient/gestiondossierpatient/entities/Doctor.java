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
@Table(name = "doctor")
public class Doctor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private User user;

    @ManyToOne
    private DoctorSpeciality speciality;

    private static final String role = AuthoritiesConstants.DOCTOR;

}
