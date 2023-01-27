package com.gestion_dossier_patient.gestiondossierpatient.services;

import com.gestion_dossier_patient.gestiondossierpatient.model.TestModel;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class TestService {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveOneTest(){
        entityManager.persist(new TestModel("Lamine", "Gueye"));
    }
}
