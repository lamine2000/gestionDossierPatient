package com.gestion_dossier_patient.gestiondossierpatient.resources;

import com.gestion_dossier_patient.gestiondossierpatient.services.TestService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/test")
public class TestResource {

    @Inject
    TestService testService;

    @GET
    @Path("/hello")
    @Produces(MediaType.APPLICATION_JSON)
    public void hello() {
        testService.saveOneTest();
    }
}
