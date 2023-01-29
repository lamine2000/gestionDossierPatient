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
@Table(name = "access_complete_medical_file_request")
public class AccessCompleteMedicalFileRequest implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    private Date createdAt;

    @ManyToOne
    private Doctor madeBy;

    @ManyToOne
    private Patient receiver;
}

