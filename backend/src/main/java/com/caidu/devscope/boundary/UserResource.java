package com.caidu.devscope.boundary;

import com.caidu.devscope.control.UserService;
import com.caidu.devscope.dto.UserDTO;
import com.caidu.devscope.exception.UserNotFoundException;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class UserResource {

    @Inject UserService userService;

    @GET
    public List<UserDTO> getAllUsers() {
        return userService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") Long id) {
        try {
            UserDTO user = userService.findById(id);
            return Response.ok(user).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response createUser(UserDTO userDTO) {
        return Response.status(Response.Status.CREATED).entity(userService.create(userDTO)).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") Long id, UserDTO userDTO) {
        try {
            UserDTO user = userService.update(id, userDTO);
            return Response.ok(user).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        if (userService.delete(id)) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
