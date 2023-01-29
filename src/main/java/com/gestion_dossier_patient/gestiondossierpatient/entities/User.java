package com.gestion_dossier_patient.gestiondossierpatient.entities;

import com.gestion_dossier_patient.gestiondossierpatient.entities.enumarations.EnumMaritalStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String cni;
    private String name;
    private String surname;
    private String email;
    private String username;
    private String password;
    private String phone;
    private String address;
    private Date dateOfBirth;
    @Enumerated(EnumType.STRING)
    private EnumMaritalStatus maritalStatus;
}
