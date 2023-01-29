package com.gestion_dossier_patient.gestiondossierpatient.entities;

import com.gestion_dossier_patient.gestiondossierpatient.entities.enumarations.EnumHospitalBedStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hospital_bed")
public class HospitalBed implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private EnumHospitalBedStatus status;

    @ManyToOne
    private HealthCenter healthCenter;
}
