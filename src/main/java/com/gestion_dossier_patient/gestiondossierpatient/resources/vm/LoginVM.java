package com.gestion_dossier_patient.gestiondossierpatient.resources.vm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVM {
    private String username;
    private String password;
}
