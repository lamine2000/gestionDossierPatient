package com.gestion_dossier_patient.gestiondossierpatient.resources;

import com.gestion_dossier_patient.gestiondossierpatient.entities.FeedBack;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import com.gestion_dossier_patient.gestiondossierpatient.services.FeedBackService;

import java.util.List;

@Path("/feedbacks")
public class FeedBackResource {
    @Inject
    private FeedBackService feedbackService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFeedBacks() {
        List<FeedBack> feedbacks = feedbackService.findAll();
        return Response.ok(feedbacks).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFeedBack(@PathParam("id") Long id) {
        FeedBack feedback = feedbackService.findById(id);
        if (feedback != null) {
            return Response.ok(feedback).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createFeedBack(FeedBack feedback) {
        feedback = feedbackService.create(feedback);
        return Response.ok(feedback).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateFeedBack(@PathParam("id") Long id, FeedBack feedback) {
        feedback = feedbackService.update(id, feedback);
        if (feedback != null) {
            return Response.ok(feedback).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteFeedBack(@PathParam("id") Long id) {
        boolean deleted = feedbackService.delete(id);
        if (deleted) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
