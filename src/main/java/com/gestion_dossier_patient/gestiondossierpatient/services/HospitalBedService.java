package com.gestion_dossier_patient.gestiondossierpatient.services;

import com.gestion_dossier_patient.gestiondossierpatient.entities.HospitalBed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class HospitalBedService {
    @PersistenceContext
    private EntityManager entityManager;

    public List<HospitalBed> findAll() {
        return entityManager
            .createQuery("SELECT h FROM HospitalBed h", HospitalBed.class)
            .getResultList();
    }

    public HospitalBed findById(Long id) {
        return entityManager.find(HospitalBed.class, id);
    }

    @Transactional
    public HospitalBed create(HospitalBed hospitalBed) {
        entityManager.persist(hospitalBed);
        return hospitalBed;
    }

    @Transactional
    public HospitalBed update(Long id, HospitalBed hospitalBed) {
        HospitalBed existingHospitalBed = entityManager.find(HospitalBed.class, id);

        if(existingHospitalBed == null)
            return null;

        existingHospitalBed = hospitalBed;
        existingHospitalBed.setId(id);
        return entityManager.merge(existingHospitalBed);
    }

    @Transactional
    public boolean delete(Long id) {
        HospitalBed existingHospitalBed = entityManager.find(HospitalBed.class, id);

        if (existingHospitalBed == null)
            return false;

        entityManager.remove(existingHospitalBed);
        return true;
    }
}
