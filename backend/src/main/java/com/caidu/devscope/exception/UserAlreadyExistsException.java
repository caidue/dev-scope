package com.caidu.devscope.exception;

import jakarta.ws.rs.core.Response;

public class UserAlreadyExistsException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public UserAlreadyExistsException(String username) {
        super(
                "User with username " + username + " already exists",
                Response.Status.CONFLICT.getStatusCode());
    }
}
