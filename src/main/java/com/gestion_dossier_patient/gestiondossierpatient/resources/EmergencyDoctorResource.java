package com.gestion_dossier_patient.gestiondossierpatient.resources;

import com.gestion_dossier_patient.gestiondossierpatient.entities.EmergencyDoctor;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import com.gestion_dossier_patient.gestiondossierpatient.services.EmergencyDoctorService;

import java.util.List;

@Path("/emergency-doctors")
public class EmergencyDoctorResource {
    @Inject
    private EmergencyDoctorService emergencyDoctorService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmergencyDoctors() {
        List<EmergencyDoctor> emergencyDoctors = emergencyDoctorService.findAll();
        return Response.ok(emergencyDoctors).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmergencyDoctor(@PathParam("id") Long id) {
        EmergencyDoctor emergencyDoctor = emergencyDoctorService.findById(id);
        if (emergencyDoctor != null) {
            return Response.ok(emergencyDoctor).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEmergencyDoctor(EmergencyDoctor emergencyDoctor) {
        emergencyDoctor = emergencyDoctorService.create(emergencyDoctor);
        return Response.ok(emergencyDoctor).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmergencyDoctor(@PathParam("id") Long id, EmergencyDoctor emergencyDoctor) {
        emergencyDoctor = emergencyDoctorService.update(id, emergencyDoctor);
        if (emergencyDoctor != null) {
            return Response.ok(emergencyDoctor).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEmergencyDoctor(@PathParam("id") Long id) {
        boolean deleted = emergencyDoctorService.delete(id);
        if (deleted) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
