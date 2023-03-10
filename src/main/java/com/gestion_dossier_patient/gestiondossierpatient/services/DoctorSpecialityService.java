package com.gestion_dossier_patient.gestiondossierpatient.services;

import com.gestion_dossier_patient.gestiondossierpatient.entities.DoctorSpeciality;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;


import java.util.List;

@ApplicationScoped
public class DoctorSpecialityService {
    @PersistenceContext
    private EntityManager entityManager;

    public List<DoctorSpeciality> findAll() {
        return entityManager
            .createQuery("SELECT d FROM DoctorSpeciality d", DoctorSpeciality.class)
            .getResultList();
    }

    public DoctorSpeciality findById(Long id) {
        return entityManager.find(DoctorSpeciality.class, id);
    }

    @Transactional
    public DoctorSpeciality create(DoctorSpeciality doctorSpeciality) {
        entityManager.persist(doctorSpeciality);
        return doctorSpeciality;
    }

    @Transactional

    public DoctorSpeciality update(Long id, DoctorSpeciality doctorSpeciality) {
        DoctorSpeciality existingDoctorSpeciality = entityManager.find(DoctorSpeciality.class, id);

        if(existingDoctorSpeciality == null)
            return null;

        existingDoctorSpeciality = doctorSpeciality;
        existingDoctorSpeciality.setId(id);
        return entityManager.merge(existingDoctorSpeciality);
    }

    @Transactional
    public boolean delete(Long id) {
        DoctorSpeciality existingDoctorSpeciality = entityManager.find(DoctorSpeciality.class, id);

        if (existingDoctorSpeciality == null)
            return false;

        entityManager.remove(existingDoctorSpeciality);
        return true;
    }
}
