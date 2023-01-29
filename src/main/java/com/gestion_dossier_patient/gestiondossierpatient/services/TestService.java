package com.gestion_dossier_patient.gestiondossierpatient.services;

import com.gestion_dossier_patient.gestiondossierpatient.entities.TestModel;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class TestService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveOneTest(){
        entityManager.persist(new TestModel("Lamine", "Gueye"));
    }
}
