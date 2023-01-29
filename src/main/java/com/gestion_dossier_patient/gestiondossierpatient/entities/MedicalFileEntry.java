package com.gestion_dossier_patient.gestiondossierpatient.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medical_file_entry")
public class MedicalFileEntry implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private MedicalFile medicalFile;

    private Date createdAt;

    private String title;

    private String description;

}
