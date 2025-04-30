package com.caidu.devscope.boundary;

import com.caidu.devscope.control.AuthService;
import com.caidu.devscope.dto.UserDTO;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Optional;

@Path("/api/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject AuthService authService;

    @GET
    @Path("/me")
    @Authenticated
    public Response getCurrentUser() {
        Optional<UserDTO> user = authService.getCurrentUser();
        if (user.isPresent()) {
            return Response.ok(user.get()).build();
        } else {
            // User not in local database, sync from Keycloak
            try {
                UserDTO syncedUser = authService.syncUserFromKeycloak();
                return Response.ok(syncedUser).build();
            } catch (Exception e) {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        }
    }

    @POST
    @Path("/sync")
    @Authenticated
    public Response syncUser() {
        try {
            UserDTO user = authService.syncUserFromKeycloak();
            return Response.ok(user).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Failed to sync user: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/roles")
    @Authenticated
    public Response getUserRoles() {
        return Response.ok(
                        new UserRolesResponse(
                                authService.hasRole("admin"),
                                authService.hasRole("user"),
                                authService.getUsername()))
                .build();
    }

    public record UserRolesResponse(boolean isAdmin, boolean isUser, String username) {}
}
