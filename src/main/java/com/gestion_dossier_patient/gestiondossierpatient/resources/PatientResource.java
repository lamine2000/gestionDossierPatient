package com.gestion_dossier_patient.gestiondossierpatient.resources;

import com.gestion_dossier_patient.gestiondossierpatient.entities.Patient;
import com.gestion_dossier_patient.gestiondossierpatient.services.PatientService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/patients")
public class PatientResource {

    @Inject
    private PatientService patientService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Patient> getPatients() {
        return patientService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Patient getPatientById(@PathParam("id") long id) {
        return patientService.findById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPatient(Patient patient) {
        patientService.create(patient);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePatient(@PathParam("id") long id, Patient patient) {
        patientService.update(id, patient);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePatient(@PathParam("id") long id) {
        patientService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
