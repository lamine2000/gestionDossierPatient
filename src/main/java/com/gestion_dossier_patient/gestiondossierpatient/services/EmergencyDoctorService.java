package com.gestion_dossier_patient.gestiondossierpatient.services;

import com.gestion_dossier_patient.gestiondossierpatient.entities.EmergencyDoctor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class EmergencyDoctorService {
    @PersistenceContext
    private EntityManager entityManager;

    public List<EmergencyDoctor> findAll() {
        return entityManager
            .createQuery("SELECT e FROM EmergencyDoctor e", EmergencyDoctor.class)
            .getResultList();
    }

    public EmergencyDoctor findById(Long id) {
        return entityManager.find(EmergencyDoctor.class, id);
    }

    @Transactional
    public EmergencyDoctor create(EmergencyDoctor emergencyDoctor) {
        entityManager.persist(emergencyDoctor);
        return emergencyDoctor;
    }

    @Transactional
    public EmergencyDoctor update(Long id, EmergencyDoctor emergencyDoctor) {
        EmergencyDoctor existingEmergencyDoctor = entityManager.find(EmergencyDoctor.class, id);

        if(existingEmergencyDoctor == null)
            return null;

        existingEmergencyDoctor = emergencyDoctor;
        existingEmergencyDoctor.setId(id);
        return entityManager.merge(existingEmergencyDoctor);
    }

    @Transactional
    public boolean delete(Long id) {
        EmergencyDoctor existingEmergencyDoctor = entityManager.find(EmergencyDoctor.class, id);

        if (existingEmergencyDoctor == null)
            return false;

        entityManager.remove(existingEmergencyDoctor);
        return true;
    }
}
