package com.gestion_dossier_patient.gestiondossierpatient.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

import com.gestion_dossier_patient.gestiondossierpatient.entities.Notification;
import com.gestion_dossier_patient.gestiondossierpatient.services.NotificationService;


@Path("/notifications")
public class NotificationResource {
    @Inject
    private NotificationService notificationService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllNotifications() {
        List<Notification> notifications = notificationService.findAll();
        return Response.ok(notifications).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotification(@PathParam("id") Long id) {
        Notification notification = notificationService.findById(id);
        if (notification != null) {
            return Response.ok(notification).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNotification(Notification notification) {
        notification = notificationService.create(notification);
        return Response.ok(notification).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateNotification(@PathParam("id") Long id, Notification notification) {
        notification = notificationService.update(id, notification);
        if (notification != null) {
            return Response.ok(notification).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteNotification(@PathParam("id") Long id) {
        boolean deleted = notificationService.delete(id);
        if (deleted) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
