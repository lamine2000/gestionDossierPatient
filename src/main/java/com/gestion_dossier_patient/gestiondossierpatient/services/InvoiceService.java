package com.gestion_dossier_patient.gestiondossierpatient.services;

import com.gestion_dossier_patient.gestiondossierpatient.entities.Invoice;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class InvoiceService {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Invoice> findAll() {
        return entityManager
            .createQuery("SELECT i FROM Invoice i", Invoice.class)
            .getResultList();
    }

    public Invoice findById(Long id) {
        return entityManager.find(Invoice.class, id);
    }

    @Transactional
    public Invoice create(Invoice invoice) {
        entityManager.persist(invoice);
        return invoice;
    }

    @Transactional
    public Invoice update(Long id, Invoice invoice) {
        Invoice existingInvoice = entityManager.find(Invoice.class, id);

        if(existingInvoice == null)
            return null;

        existingInvoice = invoice;
        existingInvoice.setId(id);
        return entityManager.merge(existingInvoice);
    }

    @Transactional
    public boolean delete(Long id) {
        Invoice existingInvoice = entityManager.find(Invoice.class, id);

        if (existingInvoice == null)
            return false;

        entityManager.remove(existingInvoice);
        return true;
    }
}
