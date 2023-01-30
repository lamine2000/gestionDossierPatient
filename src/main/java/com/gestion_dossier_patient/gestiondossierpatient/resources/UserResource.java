package com.gestion_dossier_patient.gestiondossierpatient.resources;

import com.gestion_dossier_patient.gestiondossierpatient.entities.User;
import com.gestion_dossier_patient.gestiondossierpatient.resources.vm.LoginVM;
import com.gestion_dossier_patient.gestiondossierpatient.services.UserService;
import jakarta.annotation.security.DenyAll;

import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/users")
public class UserResource {

    @Inject
    private UserService userService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<User> users = userService.findAll();
        return Response.ok(users).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @DenyAll
    public Response getUser(@PathParam("id") Long id) {
        User user = userService.findById(id);
        if (user != null)
            return Response.ok(user).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response createUser(@NotNull User user) {
        user = userService.create(user);
        return Response.ok(user).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") Long id, @NotNull User user) {
        user = userService.update(id, user);
        if (user != null)
            return Response.ok(user).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") Long id) {
        boolean success = userService.delete(id);
        if (success)
            return Response.ok().build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    //login user
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response login(@NotNull LoginVM user) {
        //make the login method return the token
        final String token = userService.login(user);
        if (token != null)
            return Response.ok(token).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
