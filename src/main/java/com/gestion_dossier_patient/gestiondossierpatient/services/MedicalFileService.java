package com.gestion_dossier_patient.gestiondossierpatient.services;

import com.gestion_dossier_patient.gestiondossierpatient.entities.MedicalFile;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class MedicalFileService {
    @PersistenceContext
    private EntityManager entityManager;

    public List<MedicalFile> findAll() {
        return entityManager
            .createQuery("SELECT m FROM MedicalFile m", MedicalFile.class)
            .getResultList();
    }

    public MedicalFile findById(Long id) {
        return entityManager.find(MedicalFile.class, id);
    }

    public MedicalFile create(MedicalFile medicalFile) {
        entityManager.persist(medicalFile);
        return medicalFile;
    }

    public MedicalFile update(Long id, MedicalFile medicalFile) {
        MedicalFile existingMedicalFile = entityManager.find(MedicalFile.class, id);

        if(existingMedicalFile == null)
            return null;

        existingMedicalFile = medicalFile;
        existingMedicalFile.setId(id);
        return entityManager.merge(existingMedicalFile);
    }

    public boolean delete(Long id) {
        MedicalFile existingMedicalFile = entityManager.find(MedicalFile.class, id);

        if (existingMedicalFile == null)
            return false;

        entityManager.remove(existingMedicalFile);
        return true;
    }
}
