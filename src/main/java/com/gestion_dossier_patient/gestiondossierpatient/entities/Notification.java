package com.gestion_dossier_patient.gestiondossierpatient.entities;

import com.gestion_dossier_patient.gestiondossierpatient.entities.enumarations.EnumNotifiationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notification")
public class Notification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Doctor sender;

    private String title;

    private String message;

    @Enumerated(EnumType.STRING)
    private EnumNotifiationStatus status;
}
