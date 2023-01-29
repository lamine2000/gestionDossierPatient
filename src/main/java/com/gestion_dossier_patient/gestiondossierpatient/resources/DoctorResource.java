package com.gestion_dossier_patient.gestiondossierpatient.resources;

import com.gestion_dossier_patient.gestiondossierpatient.entities.Doctor;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import com.gestion_dossier_patient.gestiondossierpatient.services.DoctorService;

import java.util.List;

@Path("/doctors")
public class DoctorResource {
    @Inject
    private DoctorService doctorService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDoctors() {
        List<Doctor> doctors = doctorService.findAll();
        return Response.ok(doctors).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDoctor(@PathParam("id") Long id) {
        Doctor doctor = doctorService.findById(id);
        if (doctor != null) {
            return Response.ok(doctor).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDoctor(Doctor doctor) {
        doctor = doctorService.create(doctor);
        return Response.ok(doctor).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDoctor(@PathParam("id") Long id, Doctor doctor) {
        doctor = doctorService.update(id, doctor);
        if (doctor != null) {
            return Response.ok(doctor).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDoctor(@PathParam("id") Long id) {
        boolean deleted = doctorService.delete(id);
        if (deleted) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
