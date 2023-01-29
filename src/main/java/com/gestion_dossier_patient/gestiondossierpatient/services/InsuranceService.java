package com.gestion_dossier_patient.gestiondossierpatient.services;

import com.gestion_dossier_patient.gestiondossierpatient.entities.Insurance;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class InsuranceService {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Insurance> findAll() {
        return entityManager
            .createQuery("SELECT i FROM Insurance i", Insurance.class)
            .getResultList();
    }

    public Insurance findById(Long id) {
        return entityManager.find(Insurance.class, id);
    }

    public Insurance create(Insurance insurance) {
        entityManager.persist(insurance);
        return insurance;
    }

    public Insurance update(Long id, Insurance insurance) {
        Insurance existingInsurance = entityManager.find(Insurance.class, id);

        if(existingInsurance == null)
            return null;

        existingInsurance = insurance;
        existingInsurance.setId(id);
        return entityManager.merge(existingInsurance);
    }

    public boolean delete(Long id) {
        Insurance existingInsurance = entityManager.find(Insurance.class, id);

        if (existingInsurance == null)
            return false;

        entityManager.remove(existingInsurance);
        return true;
    }
}
