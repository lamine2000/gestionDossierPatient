package com.gestion_dossier_patient.gestiondossierpatient.security;

import com.gestion_dossier_patient.gestiondossierpatient.entities.User;
import com.gestion_dossier_patient.gestiondossierpatient.services.UserService;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationException;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.CallerPrincipal;
import jakarta.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import jakarta.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStoreHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.Priorities;

import java.util.Base64;
import java.util.Optional;
import java.util.Set;
import java.util.StringTokenizer;

import static jakarta.security.enterprise.identitystore.CredentialValidationResult.Status.VALID;
@Priority(Priorities.AUTHENTICATION)
@ApplicationScoped
public class CustomAuthenticationMechanism implements HttpAuthenticationMechanism {
    @Inject
    private IdentityStoreHandler identityStoreHandler;

    @Inject
    private UserService userService;

    @Override
    public AuthenticationStatus validateRequest(
        HttpServletRequest request,
        HttpServletResponse response,
        HttpMessageContext httpMessageContext) throws AuthenticationException {

        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            // Extract the JWT from the header
            String jwt = authorizationHeader.substring(7);

            // Validate the JWT
            CredentialValidationResult result = validateJWT(jwt);
            if (result.getStatus() == VALID) {
                return httpMessageContext.notifyContainerAboutLogin(result);
            }
        }

        // Return UNAUTHENTICATED to trigger the login form
        return httpMessageContext.doNothing();
    }

    private CredentialValidationResult validateJWT(String jwt) {
        // Implement JWT validation logic here
        //retreve the username and password from the jwt
        String usernamePassword = new String(Base64.getDecoder().decode(jwt));
        // Split username and password tokens
        final StringTokenizer tokenizer = new StringTokenizer(usernamePassword, ":");
        final String username = tokenizer.nextToken();
        final String password = tokenizer.nextToken();

        Optional<User> optUser = userService.findByUsername(username);

        //check if the user exist in the database
        if(optUser.isEmpty()){
            return new CredentialValidationResult("");
        }

        //check if the password is correct
        if(!optUser.get().getPassword().equals(password)){
            return new CredentialValidationResult("");
        }

        //username and password are correct !
        return new CredentialValidationResult(
            "",
            new CallerPrincipal(username),
            "",
            String.valueOf(optUser.get().getId()),
            Set.of("USER"));
    }
}
