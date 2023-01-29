package com.gestion_dossier_patient.gestiondossierpatient.services;

import com.gestion_dossier_patient.gestiondossierpatient.entities.Doctor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class DoctorService {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Doctor> findAll() {
        return entityManager
            .createQuery("SELECT d FROM Doctor d", Doctor.class)
            .getResultList();
    }

    public Doctor findById(Long id) {
        return entityManager.find(Doctor.class, id);
    }

    @Transactional
    public Doctor create(Doctor doctor) {
        entityManager.persist(doctor);
        return doctor;
    }

    @Transactional
    public Doctor update(Long id, Doctor doctor) {
        Doctor existingDoctor = entityManager.find(Doctor.class, id);

        if(existingDoctor == null)
            return null;

        existingDoctor = doctor;
        existingDoctor.setId(id);
        return entityManager.merge(existingDoctor);
    }

    @Transactional
    public boolean delete(Long id) {
        Doctor existingDoctor = entityManager.find(Doctor.class, id);

        if (existingDoctor == null)
            return false;

        entityManager.remove(existingDoctor);
        return true;
    }
}
