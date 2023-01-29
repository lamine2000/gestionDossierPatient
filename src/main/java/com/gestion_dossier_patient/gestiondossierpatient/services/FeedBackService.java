package com.gestion_dossier_patient.gestiondossierpatient.services;

import com.gestion_dossier_patient.gestiondossierpatient.entities.FeedBack;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class FeedBackService {
    @PersistenceContext
    private EntityManager entityManager;

    public List<FeedBack> findAll() {
        return entityManager
            .createQuery("SELECT f FROM FeedBack f", FeedBack.class)
            .getResultList();
    }

    public FeedBack findById(Long id) {
        return entityManager.find(FeedBack.class, id);
    }

    @Transactional
    public FeedBack create(FeedBack feedBack) {
        entityManager.persist(feedBack);
        return feedBack;
    }

    @Transactional
    public FeedBack update(Long id, FeedBack feedBack) {
        FeedBack existingFeedBack = entityManager.find(FeedBack.class, id);

        if(existingFeedBack == null)
            return null;

        existingFeedBack = feedBack;
        existingFeedBack.setId(id);
        return entityManager.merge(existingFeedBack);
    }

    @Transactional
    public boolean delete(Long id) {
        FeedBack existingFeedBack = entityManager.find(FeedBack.class, id);

        if (existingFeedBack == null)
            return false;

        entityManager.remove(existingFeedBack);
        return true;
    }
}
