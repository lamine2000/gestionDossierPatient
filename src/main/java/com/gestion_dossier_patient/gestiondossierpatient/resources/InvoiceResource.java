package com.gestion_dossier_patient.gestiondossierpatient.resources;

import com.gestion_dossier_patient.gestiondossierpatient.entities.Invoice;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import com.gestion_dossier_patient.gestiondossierpatient.services.InvoiceService;

import java.util.List;

@Path("/invoices")
public class InvoiceResource {
    @Inject
    private InvoiceService invoiceService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllInvoices() {
        List<Invoice> invoices = invoiceService.findAll();
        return Response.ok(invoices).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInvoice(@PathParam("id") Long id) {
        Invoice invoice = invoiceService.findById(id);
        if (invoice != null) {
            return Response.ok(invoice).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createInvoice(Invoice invoice) {
        invoice = invoiceService.create(invoice);
        return Response.ok(invoice).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateInvoice(@PathParam("id") Long id, Invoice invoice) {
        invoice = invoiceService.update(id, invoice);
        if (invoice != null) {
            return Response.ok(invoice).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteInvoice(@PathParam("id") Long id) {
        invoiceService.delete(id);
        return Response.ok().build();
    }

}
