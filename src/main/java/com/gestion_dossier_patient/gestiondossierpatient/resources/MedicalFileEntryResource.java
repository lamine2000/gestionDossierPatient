package com.gestion_dossier_patient.gestiondossierpatient.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

import com.gestion_dossier_patient.gestiondossierpatient.entities.MedicalFileEntry;
import com.gestion_dossier_patient.gestiondossierpatient.services.MedicalFileEntryService;

@Path("/medical-entries")
public class MedicalFileEntryResource {
    @Inject
    private MedicalFileEntryService medicalFileEntryService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMedicalEntries() {
        List<MedicalFileEntry> medcalFileEntries = medicalFileEntryService.findAll();
        return Response.ok(medcalFileEntries).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMedicalFileEntry(@PathParam("id") Long id) {
        MedicalFileEntry medicalFileEntry = medicalFileEntryService.findById(id);
        if (medicalFileEntry != null) {
            return Response.ok(medicalFileEntry).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMedicalFileEntry(MedicalFileEntry medicalFileEntry) {
        medicalFileEntry = medicalFileEntryService.create(medicalFileEntry);
        return Response.ok(medicalFileEntry).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMedicalFileEntry(@PathParam("id") Long id, MedicalFileEntry medicalFileEntry) {
        medicalFileEntry = medicalFileEntryService.update(id, medicalFileEntry);
        if (medicalFileEntry != null) {
            return Response.ok(medicalFileEntry).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMedicalFileEntry(@PathParam("id") Long id) {
        boolean deleted = medicalFileEntryService.delete(id);
        if (deleted) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
