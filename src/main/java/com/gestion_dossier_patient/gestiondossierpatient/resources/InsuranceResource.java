package com.gestion_dossier_patient.gestiondossierpatient.resources;

import com.gestion_dossier_patient.gestiondossierpatient.entities.Insurance;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import com.gestion_dossier_patient.gestiondossierpatient.services.InsuranceService;

import java.util.List;

@Path("/insurances")
public class InsuranceResource {
    @Inject
    private InsuranceService insuranceService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllInsurances() {
        List<Insurance> insurances = insuranceService.findAll();
        return Response.ok(insurances).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInsurance(@PathParam("id") Long id) {
        Insurance insurance = insuranceService.findById(id);
        if (insurance != null) {
            return Response.ok(insurance).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createInsurance(Insurance insurance) {
        insurance = insuranceService.create(insurance);
        return Response.ok(insurance).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateInsurance(@PathParam("id") Long id, Insurance insurance) {
        insurance = insuranceService.update(id, insurance);
        if (insurance != null) {
            return Response.ok(insurance).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteInsurance(@PathParam("id") Long id) {
        boolean deleted = insuranceService.delete(id);
        if (deleted) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
