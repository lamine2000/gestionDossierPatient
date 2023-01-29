package com.gestion_dossier_patient.gestiondossierpatient.resources;

import com.gestion_dossier_patient.gestiondossierpatient.entities.MedicalFile;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import com.gestion_dossier_patient.gestiondossierpatient.services.MedicalFileService;

import java.util.List;

@Path("/medicalFiles")
public class MedicalFileResource {
    @Inject
    private MedicalFileService medicalFileService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMedicalFiles() {
        List<MedicalFile> medicalFiles = medicalFileService.findAll();
        return Response.ok(medicalFiles).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMedicalFile(@PathParam("id") Long id) {
        MedicalFile medicalFile = medicalFileService.findById(id);
        if (medicalFile != null) {
            return Response.ok(medicalFile).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMedicalFile(MedicalFile medicalFile) {
        medicalFile = medicalFileService.create(medicalFile);
        return Response.ok(medicalFile).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMedicalFile(@PathParam("id") Long id, MedicalFile medicalFile) {
        medicalFile = medicalFileService.update(id, medicalFile);
        if (medicalFile != null) {
            return Response.ok(medicalFile).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMedicalFile(@PathParam("id") Long id) {
        boolean deleted = medicalFileService.delete(id);
        if (deleted) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
