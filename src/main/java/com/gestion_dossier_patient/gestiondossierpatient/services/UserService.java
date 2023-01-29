package com.gestion_dossier_patient.gestiondossierpatient.services;

import com.gestion_dossier_patient.gestiondossierpatient.entities.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class UserService {
    @PersistenceContext
    private EntityManager entityManager;

    public List<User> findAll() {
        return entityManager
            .createQuery("SELECT u FROM User u", User.class)
            .getResultList();
    }

    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    public User create(User user) {
        entityManager.persist(user);
        return user;
    }

    @Transactional
    public User update(Long id, User user) {
        User existingUser = entityManager.find(User.class, id);

        if(existingUser == null)
            return null;

        existingUser = user;
        existingUser.setId(id);
        return entityManager.merge(existingUser);
    }

    @Transactional
    public boolean delete(Long id) {
        User existingUser = entityManager.find(User.class, id);

        if (existingUser == null)
            return false;

        entityManager.remove(existingUser);
        return true;
    }
}
