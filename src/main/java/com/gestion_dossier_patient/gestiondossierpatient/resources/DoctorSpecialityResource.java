package com.gestion_dossier_patient.gestiondossierpatient.resources;

import com.gestion_dossier_patient.gestiondossierpatient.entities.DoctorSpeciality;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import com.gestion_dossier_patient.gestiondossierpatient.services.DoctorSpecialityService;

import java.util.List;

@Path("/doctor_specialities")
public class DoctorSpecialityResource {
    @Inject
    private DoctorSpecialityService doctorSpecialityService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDoctorSpecialities() {
        List<DoctorSpeciality> doctorSpecialities = doctorSpecialityService.findAll();
        return Response.ok(doctorSpecialities).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDoctorSpeciality(@PathParam("id") Long id) {
        DoctorSpeciality doctorSpeciality = doctorSpecialityService.findById(id);
        if (doctorSpeciality != null) {
            return Response.ok(doctorSpeciality).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDoctorSpeciality(DoctorSpeciality doctorSpeciality) {
        doctorSpeciality = doctorSpecialityService.create(doctorSpeciality);
        return Response.ok(doctorSpeciality).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDoctorSpeciality(@PathParam("id") Long id, DoctorSpeciality doctorSpeciality) {
        doctorSpeciality = doctorSpecialityService.update(id, doctorSpeciality);
        if (doctorSpeciality != null) {
            return Response.ok(doctorSpeciality).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDoctorSpeciality(@PathParam("id") Long id) {
        boolean deleted = doctorSpecialityService.delete(id);
        if (deleted) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
