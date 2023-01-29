package com.gestion_dossier_patient.gestiondossierpatient.services;

import com.gestion_dossier_patient.gestiondossierpatient.entities.Care;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class CareService {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Care> findAll() {
        return entityManager
            .createQuery("SELECT c FROM Care c", Care.class)
            .getResultList();
    }

    public Care findById(Long id) {
        return entityManager.find(Care.class, id);
    }

    @Transactional
    public Care create(Care care) {
        entityManager.persist(care);
        return care;
    }

    @Transactional
    public Care update(Long id, Care care) {
        Care existingCare = entityManager.find(Care.class, id);

        if(existingCare == null)
            return null;

        existingCare = care;
        existingCare.setId(id);
        return entityManager.merge(existingCare);
    }

    @Transactional
    public boolean delete(Long id) {
        Care existingCare = entityManager.find(Care.class, id);

        if (existingCare == null)
            return false;

        entityManager.remove(existingCare);
        return true;
    }
}
