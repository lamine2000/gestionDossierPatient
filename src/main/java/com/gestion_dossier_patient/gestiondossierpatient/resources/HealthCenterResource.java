package com.gestion_dossier_patient.gestiondossierpatient.resources;

import com.gestion_dossier_patient.gestiondossierpatient.entities.HealthCenter;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import com.gestion_dossier_patient.gestiondossierpatient.services.HealthCenterService;

import java.util.List;

@Path("/healthcenters")
public class HealthCenterResource {
    @Inject
    private HealthCenterService healthCenterService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllHealthCenters() {
        List<HealthCenter> healthCenters = healthCenterService.findAll();
        return Response.ok(healthCenters).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHealthCenter(@PathParam("id") Long id) {
        HealthCenter healthCenter = healthCenterService.findById(id);
        if (healthCenter != null) {
            return Response.ok(healthCenter).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createHealthCenter(HealthCenter healthCenter) {
        healthCenter = healthCenterService.create(healthCenter);
        return Response.ok(healthCenter).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateHealthCenter(@PathParam("id") Long id, HealthCenter healthCenter) {
        healthCenter = healthCenterService.update(id, healthCenter);
        if (healthCenter != null) {
            return Response.ok(healthCenter).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteHealthCenter(@PathParam("id") Long id) {
        boolean deleted = healthCenterService.delete(id);
        if (deleted) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
