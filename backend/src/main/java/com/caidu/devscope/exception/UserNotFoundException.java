package com.caidu.devscope.exception;

import jakarta.ws.rs.core.Response;

public class UserNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public UserNotFoundException(Long id) {
        super("User with id " + id + " not found", Response.Status.NOT_FOUND.getStatusCode());
    }
}
