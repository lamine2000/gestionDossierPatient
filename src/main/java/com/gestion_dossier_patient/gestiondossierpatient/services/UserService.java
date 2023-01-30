package com.gestion_dossier_patient.gestiondossierpatient.services;

import com.gestion_dossier_patient.gestiondossierpatient.entities.Doctor;
import com.gestion_dossier_patient.gestiondossierpatient.entities.EmergencyDoctor;
import com.gestion_dossier_patient.gestiondossierpatient.entities.Patient;
import com.gestion_dossier_patient.gestiondossierpatient.entities.User;
import com.gestion_dossier_patient.gestiondossierpatient.resources.vm.LoginVM;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

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

    //get by username
    public Optional<User> findByUsername(String username) {
        return entityManager
            .createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
            .setParameter("username", username)
            .getResultStream()
            .findFirst();
    }

    //is user
    public boolean isUser(String username) {
        return findByUsername(username).isPresent();
    }

    //is patient
    public boolean isPatient(String username) {
        Optional<User> user = findByUsername(username);

        if(user.isEmpty())
            return false;

        //check if there is a patient with this user
        return entityManager
            .createQuery("SELECT p FROM Patient p WHERE p.user = :user", Patient.class)
            .setParameter("user", user.get())
            .getResultStream()
            .findFirst()
            .isPresent();
    }

    //is doctor
    public boolean isDoctor(String username) {
        Optional<User> user = findByUsername(username);

        if(user.isEmpty())
            return false;

        //check if there is a doctor with this user
        return entityManager
            .createQuery("SELECT d FROM Doctor d WHERE d.user = :user", Doctor.class)
            .setParameter("user", user.get())
            .getResultStream()
            .findFirst()
            .isPresent();
    }

    //is emergency doctor
    public boolean isEmergencyDoctor(String username) {
        Optional<User> user = findByUsername(username);

        if(user.isEmpty())
            return false;

        //check if there is a emergency doctor with this user
        return entityManager
            .createQuery("SELECT ed FROM EmergencyDoctor ed WHERE ed.user = :user", EmergencyDoctor.class)
            .setParameter("user", user.get())
            .getResultStream()
            .findFirst()
            .isPresent();
    }

    //login
    public String login(LoginVM user) {
        final String encryptedPassword = DigestUtils.sha256Hex(user.getPassword());
        user.setPassword(encryptedPassword);

        Optional<User> existingUser = findByUsername(user.getUsername());

        if(existingUser.isEmpty())
            return null;

        final User eu = existingUser.get();
        final String username = eu.getUsername();
        final String password = eu.getPassword();
        final String decodedPayload = username.concat(":").concat(password);

        if(eu.getPassword().equals(user.getPassword()))
            return Base64.getEncoder()
                .encodeToString(decodedPayload.getBytes());

        return null;
    }
}
