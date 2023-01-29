package com.gestion_dossier_patient.gestiondossierpatient.services;

import com.gestion_dossier_patient.gestiondossierpatient.entities.HealthCenter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class HealthCenterService {
    @PersistenceContext
    private EntityManager entityManager;

    public List<HealthCenter> findAll() {
        return entityManager
            .createQuery("SELECT h FROM HealthCenter h", HealthCenter.class)
            .getResultList();
    }

    public HealthCenter findById(Long id) {
        return entityManager.find(HealthCenter.class, id);
    }

    public HealthCenter create(HealthCenter healthCenter) {
        entityManager.persist(healthCenter);
        return healthCenter;
    }

    public HealthCenter update(Long id, HealthCenter healthCenter) {
        HealthCenter existingHealthCenter = entityManager.find(HealthCenter.class, id);

        if(existingHealthCenter == null)
            return null;

        existingHealthCenter = healthCenter;
        existingHealthCenter.setId(id);
        return entityManager.merge(existingHealthCenter);
    }

    public boolean delete(Long id) {
        HealthCenter existingHealthCenter = entityManager.find(HealthCenter.class, id);

        if (existingHealthCenter == null)
            return false;

        entityManager.remove(existingHealthCenter);
        return true;
    }
}
