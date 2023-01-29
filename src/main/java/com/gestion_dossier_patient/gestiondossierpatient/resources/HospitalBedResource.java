package com.gestion_dossier_patient.gestiondossierpatient.resources;

import com.gestion_dossier_patient.gestiondossierpatient.entities.HospitalBed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import com.gestion_dossier_patient.gestiondossierpatient.services.HospitalBedService;

import java.util.List;

@Path("/hospitalBeds")
public class HospitalBedResource {
    @Inject
    private HospitalBedService hospitalBedService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllHospitalBeds() {
        List<HospitalBed> hospitalBeds = hospitalBedService.findAll();
        return Response.ok(hospitalBeds).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHospitalBed(@PathParam("id") Long id) {
        HospitalBed hospitalBed = hospitalBedService.findById(id);
        if (hospitalBed != null) {
            return Response.ok(hospitalBed).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createHospitalBed(HospitalBed hospitalBed) {
        hospitalBed = hospitalBedService.create(hospitalBed);
        return Response.ok(hospitalBed).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateHospitalBed(@PathParam("id") Long id, HospitalBed hospitalBed) {
        hospitalBed = hospitalBedService.update(id, hospitalBed);
        if (hospitalBed != null) {
            return Response.ok(hospitalBed).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteHospitalBed(@PathParam("id") Long id) {
        boolean deleted = hospitalBedService.delete(id);
        if (deleted) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
