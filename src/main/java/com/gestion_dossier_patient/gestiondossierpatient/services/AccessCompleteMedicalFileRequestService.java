package com.gestion_dossier_patient.gestiondossierpatient.services;

import com.gestion_dossier_patient.gestiondossierpatient.entities.AccessCompleteMedicalFileRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class AccessCompleteMedicalFileRequestService {
    @PersistenceContext
    private EntityManager entityManager;

    public List<AccessCompleteMedicalFileRequest> findAll(){
        return entityManager
            .createQuery("SELECT a FROM AccessCompleteMedicalFileRequest a", AccessCompleteMedicalFileRequest.class)
            .getResultList();
    }

    public AccessCompleteMedicalFileRequest findById(Long id){
        return entityManager.find(AccessCompleteMedicalFileRequest.class, id);
    }

    @Transactional
    public AccessCompleteMedicalFileRequest create(AccessCompleteMedicalFileRequest accessCompleteMedicalFileRequest) {
        entityManager.persist(accessCompleteMedicalFileRequest);
        return accessCompleteMedicalFileRequest;
    }

    @Transactional
    public AccessCompleteMedicalFileRequest update(Long id, AccessCompleteMedicalFileRequest accessCompleteMedicalFileRequest) {
        AccessCompleteMedicalFileRequest existingAccessCompleteMedicalFileRequest = entityManager.find(AccessCompleteMedicalFileRequest.class, id);

        if(existingAccessCompleteMedicalFileRequest == null)
            return null;

        existingAccessCompleteMedicalFileRequest = accessCompleteMedicalFileRequest;
        existingAccessCompleteMedicalFileRequest.setId(id);
        return entityManager.merge(existingAccessCompleteMedicalFileRequest);
    }

    @Transactional
    public boolean delete(Long id) {
        AccessCompleteMedicalFileRequest existingAccessCompleteMedicalFileRequest = entityManager.find(AccessCompleteMedicalFileRequest.class, id);

        if (existingAccessCompleteMedicalFileRequest == null)
            return false;

        entityManager.remove(existingAccessCompleteMedicalFileRequest);
        return true;
    }
}
