package com.gestion_dossier_patient.gestiondossierpatient.resources;

import com.gestion_dossier_patient.gestiondossierpatient.entities.AccessCompleteMedicalFileRequest;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import com.gestion_dossier_patient.gestiondossierpatient.services.AccessCompleteMedicalFileRequestService;

import java.util.List;

@Path("/access_complete_medical_file_requests")
public class AccessCompleteMedicalFileRequestResource {
    @Inject
    private AccessCompleteMedicalFileRequestService accessCompleteMedicalFileRequestService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAccessCompleteMedicalFileRequests() {
        List<AccessCompleteMedicalFileRequest> requests = accessCompleteMedicalFileRequestService.findAll();
        return Response.ok(requests).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccessCompleteMedicalFileRequest(@PathParam("id") Long id) {
        AccessCompleteMedicalFileRequest request = accessCompleteMedicalFileRequestService.findById(id);
        if (request != null) {
            return Response.ok(request).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccessCompleteMedicalFileRequest(AccessCompleteMedicalFileRequest request) {
        request = accessCompleteMedicalFileRequestService.create(request);
        return Response.ok(request).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAccessCompleteMedicalFileRequest(@PathParam("id") Long id, AccessCompleteMedicalFileRequest request) {
        request = accessCompleteMedicalFileRequestService.update(id, request);
        if (request != null) {
            return Response.ok(request).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAccessCompleteMedicalFileRequest(@PathParam("id") Long id) {
        boolean deleted = accessCompleteMedicalFileRequestService.delete(id);
        if (deleted) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
