package com.gestion_dossier_patient.gestiondossierpatient.services;

import com.gestion_dossier_patient.gestiondossierpatient.entities.MedicalFileEntry;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class MedicalFileEntryService {
    @PersistenceContext
    private EntityManager entityManager;

    public List<MedicalFileEntry> findAll() {
        return entityManager
            .createQuery("SELECT m FROM MedicalFileEntry m", MedicalFileEntry.class)
            .getResultList();
    }

    public MedicalFileEntry findById(Long id) {
        return entityManager.find(MedicalFileEntry.class, id);
    }

    @Transactional
    public MedicalFileEntry create(MedicalFileEntry medicalFileEntry) {
        entityManager.persist(medicalFileEntry);
        return medicalFileEntry;
    }

    @Transactional
    public MedicalFileEntry update(Long id, MedicalFileEntry medicalFileEntry) {
        MedicalFileEntry existingMedicalFileEntry = entityManager.find(MedicalFileEntry.class, id);

        if(existingMedicalFileEntry == null)
            return null;

        existingMedicalFileEntry = medicalFileEntry;
        existingMedicalFileEntry.setId(id);
        return entityManager.merge(existingMedicalFileEntry);
    }

    @Transactional
    public boolean delete(Long id) {
        MedicalFileEntry existingMedicalFileEntry = entityManager.find(MedicalFileEntry.class, id);

        if (existingMedicalFileEntry == null)
            return false;

        entityManager.remove(existingMedicalFileEntry);
        return true;
    }
}
