package com.gestion_dossier_patient.gestiondossierpatient.services;

import com.gestion_dossier_patient.gestiondossierpatient.entities.Notification;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class NotificationService {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Notification> findAll() {
        return entityManager
            .createQuery("SELECT n FROM Notification n", Notification.class)
            .getResultList();
    }

    public Notification findById(Long id) {
        return entityManager.find(Notification.class, id);
    }

    public Notification create(Notification notification) {
        entityManager.persist(notification);
        return notification;
    }

    public Notification update(Long id, Notification notification) {
        Notification existingNotification = entityManager.find(Notification.class, id);

        if(existingNotification == null)
            return null;

        existingNotification = notification;
        existingNotification.setId(id);
        return entityManager.merge(existingNotification);
    }

    public boolean delete(Long id) {
        Notification existingNotification = entityManager.find(Notification.class, id);

        if (existingNotification == null)
            return false;

        entityManager.remove(existingNotification);
        return true;
    }
}
