package com.gestion_dossier_patient.gestiondossierpatient.services;

import com.gestion_dossier_patient.gestiondossierpatient.entities.Patient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class PatientService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Patient create(Patient patient) {
        entityManager.persist(patient);
        return patient;
    }

    public Patient findById(Long id) {
        return entityManager.find(Patient.class, id);
    }

    public List<Patient> findAll() {
        return entityManager
            .createQuery("SELECT p FROM Patient p", Patient.class)
            .getResultList();
    }

    @Transactional
    public Patient update(Long id, Patient patient) {
        Patient existingPatient = entityManager.find(Patient.class, id);

        if(existingPatient == null)
            return null;

        existingPatient = patient;
        existingPatient.setId(id);
        return entityManager.merge(existingPatient);
    }

    @Transactional
    public void delete(Long id) {
        Patient existingPatient = findById(id);
        entityManager.remove(existingPatient);
    }
}
