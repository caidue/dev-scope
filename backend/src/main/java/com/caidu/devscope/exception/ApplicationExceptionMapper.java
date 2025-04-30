package com.caidu.devscope.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ApplicationExceptionMapper
        implements jakarta.ws.rs.ext.ExceptionMapper<ApplicationException> {

    @Override
    public Response toResponse(ApplicationException exception) {
        return Response.status(exception.getStatusCode())
                .entity(new ErrorResponse(exception.getMessage()))
                .build();
    }

    private record ErrorResponse(String message) {}
}
