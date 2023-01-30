package com.gestion_dossier_patient.gestiondossierpatient.security;

import com.gestion_dossier_patient.gestiondossierpatient.services.UserService;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

@Provider
public class JWTConfig implements ContainerRequestFilter {
    @Context
    private ResourceInfo resourceInfo;

    @Inject
    UserService userService;

    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Bearer";
    private static final Response ACCESS_DENIED = Response.status(Response.Status.UNAUTHORIZED)
        .entity("You cannot access this resource").build();
    private static final Response ACCESS_FORBIDDEN = Response.status(Response.Status.FORBIDDEN)
        .entity("Access blocked for all users !!").build();
    private static final Response SERVER_ERROR = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity("INTERNAL SERVER ERROR").build();

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Method method = resourceInfo.getResourceMethod();
        // Access denied for all
        if (method.isAnnotationPresent(DenyAll.class)) {
            requestContext.abortWith(ACCESS_FORBIDDEN);
            return;
        }
        // Access allowed for all
        if (method.isAnnotationPresent(PermitAll.class)) {
            return;
        }
        // Get request headers
        // Fetch authorization header
        final String authorization = requestContext.getHeaders().getFirst(AUTHORIZATION_PROPERTY);
        // If no authorization information present; block access
        if (authorization == null || authorization.isEmpty()) {
            requestContext.abortWith(ACCESS_DENIED);
            return;
        }
        // Get encoded username and password
        final String encodedUserPassword = authorization.replaceFirst(AUTHENTICATION_SCHEME + " ", "");
        // Decode username and password
        String usernameAndPassword = new String(Base64.getDecoder().decode(encodedUserPassword));

        // Split username and password tokens
        final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
        final String username = tokenizer.nextToken();
        final String password = tokenizer.nextToken();
        // Verifying Username and
        if (method.isAnnotationPresent(RolesAllowed.class)) {
            RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
            List<String> rolesSet = Arrays.asList(rolesAnnotation.value());
            // Is user valid?
            if (!isUserAllowed(username, password, rolesSet)) {
                requestContext.abortWith(ACCESS_DENIED);
                return;
            }
            return;
        }
    }

    private boolean isUserAllowed(final String username, final String password, final List<String> rolesList) {
        if(userService.isPatient(username) && rolesList.contains(AuthoritiesConstants.PATIENT)){
            return true;
        }
        if(userService.isDoctor(username) && rolesList.contains(AuthoritiesConstants.DOCTOR)){
            return true;
        }
        return userService.isEmergencyDoctor(username) && rolesList.contains(AuthoritiesConstants.EMERGENCY_DOCTOR);
    }
}


